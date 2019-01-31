'use strict';

(function($) {
  jQuery(document).ready(function() {

    // Promo Popup
    var docCookies = {
      getItem: function (sKey) {
        if (!sKey) { return null; }
        return decodeURIComponent(document.cookie.replace(new RegExp("(?:(?:^|.*;)\\s*" + encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*([^;]*).*$)|^.*$"), "$1")) || null;
      },
      setItem: function (sKey, sValue, vEnd, sPath, sDomain, bSecure) {
        if (!sKey || /^(?:expires|max\-age|path|domain|secure)$/i.test(sKey)) { return false; }
        var sExpires = "";
        if (vEnd) {
          switch (vEnd.constructor) {
            case Number:
              sExpires = vEnd === Infinity ? "; expires=Fri, 31 Dec 9999 23:59:59 GMT" : "; max-age=" + vEnd;
              break;
            case String:
              sExpires = "; expires=" + vEnd;
              break;
            case Date:
              sExpires = "; expires=" + vEnd.toUTCString();
              break;
          }
        }
        document.cookie = encodeURIComponent(sKey) + "=" + encodeURIComponent(sValue) + sExpires + (sDomain ? "; domain=" + sDomain : "") + (sPath ? "; path=" + sPath : "") + (bSecure ? "; secure" : "");
        return true;
      },
      removeItem: function (sKey, sPath, sDomain) {
        if (!this.hasItem(sKey)) { return false; }
        document.cookie = encodeURIComponent(sKey) + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT" + (sDomain ? "; domain=" + sDomain : "") + (sPath ? "; path=" + sPath : "");
        return true;
      },
      hasItem: function (sKey) {
        if (!sKey || /^(?:expires|max\-age|path|domain|secure)$/i.test(sKey)) { return false; }
        return (new RegExp("(?:^|;\\s*)" + encodeURIComponent(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=")).test(document.cookie);
      },
      keys: function () {
        var aKeys = document.cookie.replace(/((?:^|\s*;)[^\=]+)(?=;|$)|^\s*|\s*(?:\=[^;]*)?(?:\1|$)/g, "").split(/\s*(?:\=[^;]*)?;\s*/);
        for (var nLen = aKeys.length, nIdx = 0; nIdx < nLen; nIdx++) { aKeys[nIdx] = decodeURIComponent(aKeys[nIdx]); }
        return aKeys;
      }
    };

    var inIframe = function() {
      try {
        return window.self !== window.top;
      } catch (e) {
          return true;
      }
    }

    var cookieKey = '_sd_demo_page_promo',
        userStatus = docCookies.getItem(cookieKey),
        $promoPopup = $('.promo-popup');

    // Show the popup if the user hasn't seen it yet.
    if (!userStatus) {
      setTimeout(function() {
        $promoPopup.addClass('bounceIn');
      }, 3000);
    } else {
      // Otherwise, just show it hidden.
      setTimeout(function() {
        $promoPopup.addClass('hidden slideInUp');
      }, 3000);
    }

    // Hide the popup
    $promoPopup.find('.close').click(function() {
      $promoPopup.addClass('hidden');

      // Save the popup as closed.
      var tomorrow = new Date();
      tomorrow.setDate(tomorrow.getDate() + 1);
      docCookies.setItem(cookieKey, true, tomorrow);
    });

    // Show the popup if closed.
    $promoPopup.find('.pp-intro-bar').click(function(e) {
      if (e.target !== this || !$(this).parent().hasClass('hidden'))
        return;
      docCookies.removeItem(cookieKey);
      $promoPopup.removeClass('hidden');
    });

    $promoPopup.find('.pp-intro-bar .up').click(function() {
      $promoPopup.removeClass('hidden');
      docCookies.removeItem(cookieKey);
    });

    $promoPopup.find('.pp-cta').click(function(e) {
      e.preventDefault();
      if (dataLayer !== 'undefined') {
        dataLayer.push( { event: 'sdp-demo-cta-upsell', data: {
          category: 'product-demo',
          action: 'cta-upsell',
          label: 'shards-dashboard-pro'
        } });
      }
      window.location = e.target.href;
    });

    //
    // Color Switcher
    //

    var $CSAccentColors = $('.color-switcher .accent-colors'),
        $mainStylesheet = $('#main-stylesheet'),
        initialStylesheet = $mainStylesheet.attr('href'),
        version = $mainStylesheet.attr('data-version'),
        initialLogo;

    $CSAccentColors.on('click', 'li', function() {
      var color = $(this).attr('data-color');
      var SSToSwitch = 'styles/accents/' + color + '.' + version + '.css';

      if (color == 'primary') {
        SSToSwitch = initialStylesheet;
      }

      $CSAccentColors.find('li.active').removeClass('active');
      $(this).addClass('active');

      // change the logo and the stylesheet
      $mainStylesheet.attr('href', SSToSwitch);
      changeLogoTo(color);

      // run these only on the blog overview page
      if (window.ubdChart !== undefined && window.BlogOverviewUsers !== undefined) {
        ubdChartChangeColor(color); // change UBD chart colors
      }

    });

    const colorSet = {
      'primary': '#007bff',
      'secondary': '#5A6169',
      'success': '#17c671',
      'info': '#00b8d8',
      'warning': '#ffb400',
      'danger': '#c4183c'
    };

    function toRGBA(color, opacity) {
      var opacity = opacity || 1;
      let c;
      if(/^#([A-Fa-f0-9]{3}){1,2}$/.test(color)) {
          c = color.substring(1).split('');
          if (c.length== 3){
              c = [c[0], c[0], c[1], c[1], c[2], c[2]];
          }
          c = '0x' + c.join('');
          return 'rgba(' + [(c>>16)&255, (c>>8)&255, c&255].join(',') + ',' + opacity + ')';
      }
    }

    function ubdChartChangeColor(color) {
      color = colorSet[color];

      ubdChart.data.datasets[0].backgroundColor = [
        toRGBA(color, 0.9),
        toRGBA(color, 0.5),
        toRGBA(color, 0.3)
      ];

      ubdChart.update();

      $('.ubd-stats__legend .ubd-stats__item:nth-child(1) i').attr('style', 'color:' + toRGBA(color, 0.9) + ';');
      $('.ubd-stats__legend .ubd-stats__item:nth-child(2) i').attr('style', 'color:' + toRGBA(color, 0.5) + ';');
      $('.ubd-stats__legend .ubd-stats__item:nth-child(3) i').attr('style', 'color:' + toRGBA(color, 0.3) + ';');
    }

    function changeLogoTo(color) {
      var $mainLogo = $('#main-logo');

      if (!initialLogo) {
        initialLogo = $mainLogo.attr('src');
      }

      if (color === 'primary') {
        $mainLogo.attr('src', initialLogo);
        return;
      }

      $mainLogo.attr('src', 'images/shards-dashboards-logo-' + color + '.svg');
    }

    // Social Share
    $('#social-share').sharrre({
      share: {
        facebook: true,
        twitter: true
      },
      buttons: {
        facebook: {
          layout: 'button_count',
          action: 'like'
        },
        twitter: {
          count: 'horizontal',
          via: 'DesignRevision',
          hashtags: 'bootstrap,uikit'
        }
      },
      enableTracking: true,
      enableHover: false,
      enableCounter: false
    });

    $('.color-switcher-toggle').click(toggleColorSwitcherClass);
    $('.color-switcher .close').click(toggleColorSwitcherClass);

    // Define tomorrow
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    docCookies.setItem(cookieKey, true, tomorrow);

    // Set the default color switcher state cookie value
    if (docCookies.getItem('_sd_cs_visible') === null) {
      docCookies.setItem('_sd_cs_visible', true, tomorrow);
    }

    // Hide the color switcher if the user closed it
    if (docCookies.getItem('_sd_cs_visible') !== 'false') {
      $('.color-switcher').addClass('visible');
    }

    function toggleColorSwitcherClass() {
      $('.color-switcher').toggleClass('visible');

      if($('.color-switcher').hasClass('visible')) {
        docCookies.setItem('_sd_cs_visible', true, tomorrow);
        return;
      }

      docCookies.setItem('_sd_cs_visible', false, tomorrow);
    }

    setTimeout(function() {
      $('.loading-overlay').fadeOut(250);
    }, 2000);

    $(document).on('click', 'a.extra-action', function(e) {
      e.preventDefault();
      e.stopPropagation();
      var href = $(this).attr('href');
      if (inIframe()) {
        window.parent.location = href;
        return;
      }
      window.location = href;
    });
  });
})(jQuery);

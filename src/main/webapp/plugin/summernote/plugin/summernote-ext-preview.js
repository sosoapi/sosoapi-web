(function (factory) {
  /* global define */
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['jquery'], factory);
  } else {
    // Browser globals: jQuery
    factory(window.jQuery);
  }
}(function ($) {
  // template
  var tmpl = $.summernote.renderer.getTemplate();

  /**
   * Show preview dialog and set event handlers on dialog controls.
   *
   * @member plugin.preview
   * @private
   * @param {jQuery} $dialog
   * @param {jQuery} $dialog
   * @param {Object} text
   * @return {Promise}
   */
  var showPreviewDialog = function ($editable, $dialog) {
    return $.Deferred(function (deferred) {
      var $previewDialog = $dialog.find('.note-preview-dialog');
      var $previewContent = $dialog.find('.note-preview-content');
      
      $previewDialog.one('shown.bs.modal', function () {
    	  $previewContent.html($editable.code());
      }).one('hidden.bs.modal', function () {
        
      }).modal('show');
    });
  };

  /**
   * @class plugin.preview
   *
   * Preview Plugin
   *
   * ### load script
   *
   * ```
   * < script src="plugin/summernote-ext-preview.js"></script >
   * ```
   *
   * ### use a plugin in toolbar
   * ```
   *    $("#editor").summernote({
   *    ...
   *    toolbar : [
   *        ['group', [ 'preview' ]]
   *    ]
   *    ...    
   *    });
   * ```
   */
  $.summernote.addPlugin({
    /** @property {String} name name of plugin */
    name: 'preview',
    /**
     * @property {Object} buttons
     * @property {function(object): string} buttons.preview
     */
    buttons: {
    	preview: function (lang, options) {
	        return tmpl.iconButton(options.iconPrefix + 'eye', {
	          event: 'showPreviewDialog',
	          title: lang.preview.preview,
	          hide: true
	        });
      }
    },

    /**
     * @property {Object} dialogs
     * @property {function(object, object): string} dialogs.preview
    */
    dialogs: {
    	preview: function (lang) {
        var body = '<div class="form-group row-fluid">' +
                     '<div class="note-preview-content"></div>' +
                   '</div>';
        var footer = '<button type="button" class="btn btn-primary note-preview-btn" data-dismiss="modal" >' + lang.preview.close + '</button>';
        return tmpl.dialog('note-preview-dialog', lang.preview.title, body, footer);
      }
    },
    
    /**
     * @property {Object} events
     * @property {Function} events.showPreviewDialog
     */
    events: {
      showPreviewDialog: function (event, editor, layoutInfo) {
        var $dialog = layoutInfo.dialog();
        var $editable = layoutInfo.editable();

        showPreviewDialog($editable, $dialog);
      }
    },

    // define language
    langs: {
      'en-US': {
        preview:{
        	preview: 'preview',
        	close: 'close',
        	title: 'Preview'
        }
      },
      'zh-CN': {
        preview:{
        	preview: '预览',
        	close: '关闭',
        	title: '预览'
        }
      }
    }
  });
}));

if (CKEDITOR.env.ie && CKEDITOR.env.version < 9)
    CKEDITOR.tools.enableHtml5Elements(document);
CKEDITOR.config.height = 150;
CKEDITOR.config.width = 'auto';
CKEDITOR.config.language = 'vi';
CKEDITOR.config.toolbarCanCollapse = true;
var initCkeditor = (function () {
    var wysiwygareaAvailable = isWysiwygareaAvailable();
    return function () {
        var editorElement = CKEDITOR.document.getById('CKeditor');
        if (wysiwygareaAvailable) {
            CKEDITOR.replace('CKeditor');
        } else {
            editorElement.setAttribute('contenteditable', 'true');
            CKEDITOR.inline('CKeditor');
        }
    };
    function isWysiwygareaAvailable() {
        if (CKEDITOR.revision == ('%RE' + 'V%')) {
            return true;
        }
        return !!CKEDITOR.plugins.get('wysiwygarea');
    }
})();
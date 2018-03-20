var exec = require('cordova/exec');

exports.hasFile = function(arg0, success, error) {
    exec(success, error, "hasFilePlugin", "hasFile", [arg0]);
};

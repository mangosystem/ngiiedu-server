'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _compose = require('recompose/compose');

var _compose2 = _interopRequireDefault(_compose);

var _withState = require('recompose/withState');

var _withState2 = _interopRequireDefault(_withState);

var _TextField = require('material-ui/TextField');

var _TextField2 = _interopRequireDefault(_TextField);

var _transformers = require('../../../../node_modules/material-ui-color-picker/lib/transformers');

var _PickerDialog = require('../../../../node_modules/material-ui-color-picker/lib/components/PickerDialog');

var _PickerDialog2 = _interopRequireDefault(_PickerDialog);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var ColorPicker = function ColorPicker(_ref) {
  var defaultValue = _ref.defaultValue,
      _onChange = _ref.onChange,
      convert = _ref.convert,
      hintText = _ref.hintText,
      floatingLabelText = _ref.floatingLabelText,
      showPicker = _ref.showPicker,
      setShowPicker = _ref.setShowPicker,
      value = _ref.value,
      setValue = _ref.setValue;
  return _react2.default.createElement(
    'div',
    null,
    _react2.default.createElement(_TextField2.default, {
      name:'colorPicker',
      hintText: hintText,
      floatingLabelText: floatingLabelText,
      inputStyle: { color: value ,background:value,width:100,height:20,marginTop:5 },
      style:{width:'90%',paddingLeft:10,paddingTop:18},
      underlineShow:false,
      onClick: function onClick() {
        return setShowPicker(true);
      },
      onChange: function onChange(e) {
        setValue(e.target.value);
        _onChange(e.target.value);
      }
    }),
    showPicker && _react2.default.createElement(_PickerDialog2.default, {
      value: value,
      onClick: function onClick() {
        setShowPicker(false);
        _onChange(value);
      },
      onChange: function onChange(c) {
        var newValue = _transformers.converters[convert](c);
        setValue(newValue);
        _onChange(newValue);
      }
    })
  );
};

ColorPicker.propTypes = {
  value: _react.PropTypes.string,
  onChange: _react.PropTypes.func,
  convert: _react.PropTypes.oneOf(Object.keys(_transformers.converters))
};

ColorPicker.defaultProps = {
  convert: _transformers.DEFAULT_CONVERTER
};

var makeColorPicker = (0, _compose2.default)((0, _withState2.default)('showPicker', 'setShowPicker', false), (0, _withState2.default)('value', 'setValue', function (_ref2) {
  var defaultValue = _ref2.defaultValue;
  return defaultValue;
}));

exports.default = makeColorPicker(ColorPicker);
module.exports = exports['default'];
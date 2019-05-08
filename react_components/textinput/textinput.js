var React = require('react');
var PropTypes = React.PropTypes;
var classNames = require('classnames');

var TextInput = React.createClass({
  getDefaultProps: function() {
    return {
      errorText:'Please fill in the field',
      value:'',
      type: 'text',
      labelText:'',
      placeholder:'',
      errorVisible: false,  
    };
  },  
  handleChange:function(e){
    this.props.onChange(e);
    
  },  
  render: function() {
    var formClass = classNames(this.props.className,'form-group', {
      'has-error' : this.props.errorVisible    
    });
        
    return (
      <div className={formClass}>      
      <label className="control-label">
      {this.props.labelText}
      </label>
      
      <input
      ref={(c) => this.input = c}
      value={this.props.value}
      onChange={this.handleChange}
      type={this.props.type}
      className="form-control"
      placeholder={this.props.placeholder} />
    
    {this.props.errorVisible ?
      <span className="help-block">
      {this.props.errorText}
      </span>
      : null }
      </div>
    );
  }
  
});

module.exports = TextInput;
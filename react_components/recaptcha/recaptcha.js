var React = require('react');
var PropTypes = React.PropTypes;
var ReCAPTCHA = require("react-google-recaptcha");
var classNames = require('classnames');

var Recaptcha = React.createClass({
  getDefaultProps: function() {
    return {
      errorText:'Please fill in the field',
      errorVisible: false
    };
  },
captchaChange:function(response){
  //if value==null,means captcha challenge has expired based by default setting
  console.log('onchange: ' + response);
  //value received if response contain value
  //if value is not empty,carry on with serverSide validation 
  var data =
  { 
    response:response,
    secret:this.props.secretKey
  };
  
  if(response!=null)
  
  $.post(this.props.serverSideValidationURL,
    {'data': JSON.stringify(data)
  },
  function(success){
    //expected server response = success : true || false
    console.log('server response:' + success);
this.props.onChange(success);
  }.bind(this));
},
  render: function() {
    return (  
      <div>
      <ReCAPTCHA
          ref="recaptcha"
          sitekey={this.props.siteKey}
          onChange={this.captchaChange}
          />       
        <br/>   
        {this.props.errorVisible ?         
          <div className="alert alert-danger" role="alert">
          <span className="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span className="sr-only">Error:</span>
        {this.props.errorText}
        </div>
      : null
      }
        </div> 
    );
  }
});

module.exports = Recaptcha;
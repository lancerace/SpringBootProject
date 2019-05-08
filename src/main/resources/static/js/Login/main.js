'use strict';
// var $ = require('jquery');
var React = require('react');
var ReactDOM = require('react-dom');
var Login = require('./react_components/login');
var PropTypes = React.PropTypes;

var Apps = React.createClass({  
  
onSubmit:function(e){
  // //prevent form submission,go through validation process first
  // console.log('working login main.js');
  // e.preventDefault();
  // //check bot || user first
  // // if(success)
  // if(this.success)
  // (usernameValue ==="" || passwordValue) ? (
  //   
  // ) : (
  //   
  // )
},
  render: function() {
    return (
      <Login
        postURL={"/diyinsurance/login"}
        title={"DIYINSURANCE"}
        siteKey={"6LdB-h4TAAAAAEvRDBYoQD4bt6tmq9ckNvIYd2Vh"}
        secretKey={"6LdB-h4TAAAAAEqr_QSbcYkcBlYeNZY0UqweTvKF"}
        onSubmit={this.onSubmit}
        />
    );
  }

});

ReactDOM.render(
  <Apps/>,
  document.getElementById('reactContainer')
);

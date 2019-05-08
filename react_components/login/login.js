var React = require('react');
var Recaptcha = require('../recaptcha');
var PropTypes = React.PropTypes;
var TextInput = require('../textinput');
var Login = React.createClass({
  //doesnt require rendering 
  data:{
    success:false,  
  },
  getInitialState: function() {
    return { 
        recaptchaErrorVisible:false,
        usernameErrorVisible:false,
        passwordErrorVisible:false,
        username:'',
        password:'',
        rememberMe:false
    };
  },
  captchaChange:function(success){  
    this.data.success = success;
    console.log('handleCaptchaChange: ' + this.data.success);
    if(success)
    this.setState({recaptchaErrorVisible:false});
  },
  commonValidate: function(stateToValidate){
    //validate empty field
    if(stateToValidate ==='undefined' || stateToValidate === '') 
    return true;
    else
    return false; 
    // this.setState({usernameErrorVisible:false});
  },
  usernameChange:function(e){  
    this.setState({
      username:e.target.value 
    });
    /* if(this.state.username ==='undefined' || this.state.username === '') 
       this.setState({usernameErrorVisible:true});
       else
       this.setState({usernameErrorVisible:false}); 
    */   
    console.log(e.target.value);
    console.log(this.state.username);
    this.setState({usernameErrorVisible:this.commonValidate(e.target.value)});
  },
  passwordChange:function(e){    
    this.setState({
      password:e.target.value 
    });  
    this.setState({passwordErrorVisible:this.commonValidate(e.target.value)});
  },
  checkBoxChange:function(e){
    console.log(e.target.checked);
    this.setState({rememberMe: e.target.checked})
  },
  validation:function(e){
    /*
    Validation flow.
    1. valid user input
    2. check recaptcha
    3. form submission  
    */
    console.log('validation');
    //1.
    this.setState({
      usernameErrorVisible:this.commonValidate(this.state.username),
      passwordErrorVisible:this.commonValidate(this.state.password)  
    });
    
    //2. setState callback invoked only after state is updated
    (this.data.success) ? this.setState({recaptchaErrorVisible:false},
      function(){
        /*
        3. submit form if all validation cleared
        https://facebook.github.io/react/docs/events.html#syntheticevent
        persist for synthetic event
        */ 
        console.log('checking rememberme stats:' + this.state.rememberMe);
        if((!this.state.usernameErrorVisible && !this.state.passwordErrorVisible && !this.state.recaptchaErrorVisible)){
          console.log('no error');
          
          //recap: using ajax asychronous to do form submission 
          //instead of <form action> because of validation check and full manipulation.
          var formData = new FormData();
          formData.append('username',this.state.password);    
          formData.append('password',this.state.username);
          formData.append('remember-me',this.state.rememberMe);
      
        //   $.ajax({
        //     url:'/diyinsurance/auth/login',
        //     type: 'POST',
        //     data: { 'username':this.state.password,
        //     'password':this.state.username
        //     ,'remember-me': this.state.rememberMe 
        //   },
        //   success: function(data) {
        //     
        //   }
        // });
          

          $.post('/diyinsurance/auth/login',
        {
            'username':this.state.username,
            'password':this.state.password,
            'remember-me': this.state.rememberMe
          }
          ,function(success,textStatus,jqXHR){
            
            console.log('login server response success');
            console.log('response status code:' + jqXHR.status);
            //if login response OK . redirect to desired webpage
            if(jqXHR.status === 200){
              console.log('response OK');
              
              
              /*pathname.substring return contextpath. Can retrieve contextpath via different method such as jsp {'<%=request.getContextPath()%>';}
              *however,jsp is a deprecated technology.Therefore, this is a better solution which does not mix with other programming language
              */
              
              window.location =window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + '/article';  
            }
            
          });
        }  
      }) : this.setState({recaptchaErrorVisible:true});
      //setState() does not immediately mutate this.state but creates a pending state transition.
      //Accessing this.state after calling this method can potentially return the existing value.
      console.log(this.data.success);
      console.log(this.state.recaptchaErrorVisible);
      
      
  },
  onSubmit:function(e){
    e.preventDefault();
    
    
    console.log('submit button clicked');
    
    this.validation(e);
  },
  componentDidUpdate(prevProps, prevState){
    //Similar to the componentDidMount, this method can be used to perform DOM operations after the data has been updated.

  },
  render: function() {
    return (
      <div className="container">
      {/*action="login" is based by URL Mapping in web.xml*/}
      <form
        ref="form"
      className="form-signin"
      action={this.props.postURL}
      method="post"
      onSubmit={this.onSubmit}> 
           
      <h2 className="form-signin-heading text-center">
      {this.props.title}
      </h2>    
    
    
      <TextInput    
      {...this.props}
      labelText="Username"    
      onChange={this.usernameChange}
      errorVisible={this.state.usernameErrorVisible}
      value={this.state.username}
      placeholder="John"
      />
    
      <TextInput    
      labelText="Password"    
      onChange={this.passwordChange}
      errorVisible={this.state.passwordErrorVisible}
      value={this.state.password}
      placeholder="****"
      type="password"
      />   
            <div className="checkbox">
              <label> 
                <input type="checkbox" name="remember-me" onChange={this.checkBoxChange} />
              Remember me
            </label>
          </div>
            <div className="form-group">
          <Recaptcha 
            siteKey={this.props.siteKey}
            secretKey={this.props.secretKey}
            onChange={this.captchaChange}
            errorText=" Recaptcha validation not cleared"
            errorVisible={this.state.recaptchaErrorVisible}
            serverSideValidationURL={"/diyinsurance/recaptchaValidation"}
         /> 
        </div>
        <button className="btn btn-lg btn-green btn-block" type="submit">Sign
          in
        </button>  
      </form>
      </div>
    );
  }

});

module.exports = Login;
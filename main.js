'use strict';
var $ = require('jquery');
var React = require('react');
var ReactDOM = require('react-dom');
var dt = require('datatables.net')(window, $);
// require('bootstrap');
require('notify')(window,$);
//react components
var DataTable = require('./react_components/datatable');

//not from bootstrap-react
// var Modal = require('./react_components/modal');
var ConfirmModal = require('./react_components/cfm_modal');
var ReactModal = require('./react_components/modal');
var Apps= React.createClass({
  getInitialState:function(){
      //app context will handle action made from dataTable and pass to modal
    return{
      action:'',
      editID:'',
      delID:'',
      //show:false for confirmation modal
      cfmShow:false,
      show:false,
      title:''
    };
  },  
handleClick:function(event){
  console.log('app context onClick');
  console.log($(event.target).data('action'));
  console.log($(event.target).data('id'));
  
  //del action
  if($(event.target).data('action')==='del'){
    this.setState({show:true,
      delID: $(event.target).data('id'),
    });
  }
  else
  {
    //add + edit
    $.post('/diyinsurance/article/getOneArticle',
    {'mArticleID': $(event.target).data('id')},
    function(response){
      console.log('getoneArticle response:');
      console.log(response);
      
      this.setState({
        title:response.mTitle,
      },function(){
        console.log('after set state for information');
      });    
    }.bind(this));    
    
    
    
    // this.setState({
    //   action:$(event.target).data('action'),
    //   editID:$(event.target).data('id'),
    //   show:true
    // });
    console.log('app action: '+this.state.action);
  }
},
handleonHide:function(e){
  console.log('onhide inoked');
    this.setState({show:false});
    //update jquery dataTables
    $('#dtables').DataTable().ajax.reload(function(){
      console.log('success  ajax reload');
    });
},
handleOnEnter:function(){
  console.log('invoked article transition in');
  
  
},
render: function() {
  return (
    <div>
      <DataTable ref="dataTable" handleClick={this.handleClick}/>
      <ConfirmModal
        bsSize="small"
        show={this.state.cfmShow}        
        onHide={this.handleonHide}
        id={this.state.delID}
        ajaxURL={"/diyinsurance/article/deleteOneArticle"}/>
      {/*<Modal
        ref="mod"
        action={this.state.action}      
        articleID={this.state.editID}    
        onSaveUrl={"/diyinsurance/article/insertOneArticle"}
        headerTitle="Article"/>*/}
        
        
        <ReactModal 
          title={this.state.title}
          show={this.state.show}
          onEnter={this.handleOnEnter}
          ref="mod"
          onSaveUrl={"/diyinsurance/article/insertOneArticle"}
          headerTitle="Article"  />
    </div>
  );}
});

ReactDOM.render(
  <Apps/>,
  document.getElementById('container')
);



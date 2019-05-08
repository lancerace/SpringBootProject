var React = require('react');
var $ = require('jquery');
var dt = require('datatables.net')(window, $);
var DataTable = React.createClass({
  // Using react component as external jquery plugin
  // React has its own virtual DOM. that's why,to work with external dom outside
  // of react, componentDidMount will do the job

opts:{
  'columnDefs': [{
    'targets': -2,
    'data': null,
    'width': '12%',
    'render':  function(data,type,row)
    {
      return '<button data-id=' + data + ' data-action="edit" class="btn btn-primary"> Edit </button>' +
      // '<button data-id=' + data + ' data-action="edit" class="btn btn-primary" data-toggle="modal" data-target="#modal"> Edit </button>' +
      '<button data-id=' + data + ' data-action="del" class="btn btn-default"> Delete </button>'
    }
  },
  {
    'targets': -1,
    'data': null,
    'visible': false
  }],
  'ajax': {
    'url': '/diyinsurance/article/getAllArticle',
    'type': 'POST',
    'dataSrc': ''
  },
  'columns':
  [
    {data: 'mArticleID'},
    {data: 'mTitle'},
    {data: 'mSummary'},
    {data: 'mKeywords'},
    {data: 'mTagName'},
    {data: 'mPublish'},
    {data: 'mDate'},
    {data: 'mArticleID'},
    {data: null}
  ],
  paging: true
},
handleClick:function(event){
  this.props.handleClick(event);  
},
    componentDidMount:function(){
    $('#dtables').DataTable(this.opts);
    //DRY exist here,solution?
    $('#dtables').on( 'click', 'button[data-action=edit], button[data-action=del]', function (event){
    this.handleClick(event);
    }.bind(this));
    $('button[data-action=add]').on( 'click', function (event){
      this.handleClick(event);
    }.bind(this));
  },
  render: function() {
    return (  
      <div className="table-responsive">

        <button className="btn btn-primary" style={{marginBottom:"15px"}} data-action="add" data-toggle="modal" data-target="#modal">New</button>
 
      <table
          id="dtables"
          className="display table table-striped table-bordered"
          cellspacing = "0"
          width = "100%">
          <thead>
            <tr>
              <th>ArticleID</th>
              <th>Title</th>
              <th>Summary</th>
              <th>Keywords</th>
              <th>
                Tag Name
              </th>
              <th>Publish</th>
              <th>Date</th>
              <th>Action</th>
              <th>Action</th>

            </tr>
          </thead>
          <tbody>

          </tbody>
        </table>
      </div>
    );
  }
})
module.exports = DataTable;

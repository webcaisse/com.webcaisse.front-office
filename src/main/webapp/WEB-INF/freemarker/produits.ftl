<#import "spring.ftl" as spring />
<head>
<#include "modules/head.ftl">	
<link rel="stylesheet" href="<@spring.url '/css/themes/default/style.min.css' />" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<@spring.url '/js/dist/jstree.min.js' />"></script>
<script>
$(function () { $('#jstree').jstree(); 

$('#jstree').on("changed.jstree", function (e, data) {
      alert(data.selected);
    });
     $('button').on('click', function () {
      $('#jstree').jstree(true).select_node('child_node_1');
      $('#jstree').jstree('select_node', 'child_node_1');
      $.jstree.reference('#jstree').select_node('child_node_1');
    });
    });
</script>
	
</head>

<body>

  <#include "common/nav.ftl">
  <div id="jstree">
    <!-- in this example the tree is populated from inline HTML -->
    <ul>
      <li>Root node 1
        <ul>
          <li id="child_node_1">Child node 1</li>
          <li>Child node 2</li>
        </ul>
      </li>
      <li>Root node 2</li>
    </ul>
  </div>
	<button>demo button</button>
	<footer>
	</footer>

</body>
</html>
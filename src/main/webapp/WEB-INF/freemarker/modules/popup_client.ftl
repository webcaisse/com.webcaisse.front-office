<head>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.min.css">
 <script type="text/javascript" language="javascript" src="//cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
 
  <script>

  $( document ).ready(function() {
	$('#listClient').dataTable();
	
});
  </script>
 
</head>
<div id="popupListClient" style="display:none; ">
<span class="button b-close" style="z-index: 88"><span>X</span></span>
<ul class="grid dark-grey-gradient" style="height: 300px; width:600px ; overflow: auto; position: relative;background-color: white; ">
<table id="listClient" class="display" style ="cellspacing:0 ; width:500px">
        <thead>
            <tr>
                <th>Nom & Prenom </th>
                <th>Telephone</th>
                <th>Adresse</th>
                
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th>Nom & Prenom </th>
                <th>Telephone</th>
                <th>Adresse</th>
            </tr>
        </tfoot>
      
         <tbody>
              <tr>
                    <td> </td>
                    <td> </td>
                    <td> </td>
              </tr>
        </tbody>
       

</table>
</ul>
</div>
<
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Dojo Contacts</title>
<link rel="stylesheet"
	href="/nviz/claro.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/dojo/1.5/dojox/grid/resources/Grid.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/dojo/1.5/dojox/grid/resources
/claroGrid.css">
<script type="text/javascript" src="/nviz/js/dojo/dojo.js" data-dojo-config="parseOnLoad: false"></script>
</head>
<script>
require([
    "dijit/MenuBar",
    "dijit/PopupMenuBarItem",
    "dijit/Menu",
    "dijit/MenuItem",
    "dijit/DropDownMenu",
    "dojo/domReady!"
], function(MenuBar, PopupMenuBarItem, Menu, MenuItem, DropDownMenu){
    var pMenuBar = new MenuBar({});

    var pSubMenu = new DropDownMenu({});
    pMenuBar.addChild(new PopupMenuBarItem({
        label: "Home",
        popup: pSubMenu
    }));

    var pSubMenu2 = new DropDownMenu({});
    pMenuBar.addChild(new PopupMenuBarItem({
        label: "My Account",
        popup: pSubMenu2
    }));

    pMenuBar.placeAt("wrapper");
    pMenuBar.startup();
});
	</script>
</head>
<body class="claro">
    <div id="wrapper" style="width: 1000px;"></div>
</body>
</html>
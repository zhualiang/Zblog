zblog.register("zblog.category");
$(function () {
    $.ajax({
        type: "GET",
        url: zblog.getDomainLink("categorys/index"),
        dataType: "json",
        success: function (data) {
            if (!data) return;
            $('#tree').treeview({data: data, nodeIcon: "glyphicon glyphicon-star-empty"});
        }
    });
});
//category分隔符
zblog.categorySplitPath="/"

zblog.category.constructSelectPath = function () {
    var treeView = $("#tree").treeview(true);
    var pathSeg = new Array();
    var selectNode = treeView.getSelected();
    selectNode=(selectNode==null)?null:selectNode[0];
    while (selectNode != null) {
        pathSeg.push(selectNode.text);
        selectNode = treeView.getParent(selectNode);
    }
    if (pathSeg.length == 0) {
        return null;
    }
    else {
        pathSeg = pathSeg.reverse();
        return pathSeg.join(zblog.categorySplitPath);
    }
}

zblog.category.insert = function () {
    var newCategory = $("#newCategory").val();
    if (!newCategory) return;

    var selectPath = zblog.category.constructSelectPath();
    $.ajax({
        type: "POST",
        url: zblog.getDomainLink("categorys"),
        dataType: "json",
        data: {parent: selectPath, name: newCategory},
        success: function (msg) {
            if (msg && msg.success) {
                window.location.reload();
                zdialog.hide('insert-box');
            } else {
                alert(msg.msg);
            }
        }
    });
}

zblog.category.remove = function () {
    var select = zblog.category.constructSelectPath();
    if (!select) return;

    $.ajax({
        type: "POST",
        url: zblog.getDomainLink("categorys/remove"),
        data: {category: select},
        dataType: "json",
        success: function (msg) {
            if (msg && msg.success) {
                window.location.reload();
            } else {
                alert("删除失败");
            }
        }
    });
}

zblog.category.edit = function () {
    var select = zblog.category.constructSelectPath();
    if (!select) return;
    var newName=$("#editCategory").val();
    var pathSegArr=select.split(zblog.categorySplitPath);
    var oldName=pathSegArr.pop();
    if(newName==null||newName==oldName)
        return;
    pathSegArr.push(newName);
    var newCategory=pathSegArr.join(zblog.categorySplitPath);
    $.ajax({
        type: "POST",
        url: zblog.getDomainLink("categorys/edit"),
        data: {oldCategory: select,newCategory:newCategory},
        dataType: "json",
        success: function (msg) {
            if (msg && msg.success) {
                window.location.reload();
            } else {
                alert("修改失败");
            }
        }
    });
}
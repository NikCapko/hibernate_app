function openIndex() {
    document.location.href = "index.html";
}

function openTask() {
    document.location.href = "task.html";
}

function openForm() {
    document.location.href = "form.html";
}

function openTable() {
    document.location.href = "table.html";
}

function sendNumber() {
    var rimNumber = $("#rim").val();
    $.get('http://127.0.0.1:8083/convert?number=' + $('#rim').val(), function (data) {
        $('#arabic').val(data.number);
    }, "json");
}

function addStudent() {
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var yearBirth = $("#yearBirth").val();
    $.post("http://127.0.0.1:8083/add_student", {firstName: firstName, lastName: lastName, yearBirth: yearBirth},
        function (data) {
            if (data.result == "success") {
                openTable();
            }
        }, "json");
}

function addField() {
    var students = null;
    $.get('http://127.0.0.1:8083/get_students_list', function (data) {
        students = data;
        showFields(students);
    }, "json");
}

function showFields(students) {
    for (var i = 0; i < students.length; i++) {
        addLine(students[i]);
    }
}

function getStudent(id) {
    $.post('http://127.0.0.1:8083/get_student', {id: id}, function (data) {
        console.log(data);
        $("#firstName").val(data.firstName);
        $("#lastName").val(data.lastName);
        $("#yearBirth").val(data.yearBirth);
    }, "json");
}

function addLine(student) {
    jQuery('.end_line').before(
        '<tr class=\"' + student.id + '\"><td>' +
        '<input type=\"text\" readonly value=' + student.firstName + '></td>' +
        '<td><input type=\"text\" readonly value=' + student.lastName + '></td>' +
        '<td><input type=\"text\" readonly value=' + student.yearBirth + '></td>' +
        '<td><span class=\"btn\" id=\"' + student.id + '\" onclick=\"deleteStudent(event)\" cursor=\"pointer\">Удалить</span></td>' +
        '<td><span class=\"btn\" id=\"' + student.id + '\" onclick=\"changeStudent(event)\" cursor=\"pointer\">Редактировать</span></td>' +
        '</tr>'
    );
}

function deleteStudent(e) {
    var sourceElem = e.target || e.srcElement;
    $('.' + sourceElem.id).remove();
    $.post("http://127.0.0.1:8083/delete_student", {id: sourceElem.id},
        function (data) {

        }, "json");
}

function changeStudent(e) {
    var sourceElem = e.target || e.srcElement;
    document.location = "form.html?id=" + sourceElem.id;
}

function changeStudentRequest(id) {
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var yearBirth = $("#yearBirth").val();
    $.post("http://127.0.0.1:8083/change_student", {id:id, firstName: firstName, lastName: lastName, yearBirth: yearBirth},
        function (data) {
            if (data.result == "success") {
                openTable();
            }
        }, "json");
}
$(function () {

    var techButton = document.querySelector("#tech-button");
    var content = document.querySelector(".content");
    var frameButton = document.querySelector("#frame-button");


    function loadTechnologies() {
        fetch('http://localhost:8080/tech/')
            .then(response => response.json())
            .then(function (result) {
                for (let i = 0; i < result.length; i++) {
                    var div = document.createElement('div');
                    div.innerHTML = result[i].name;
                    content.appendChild(div);
                }
            })
    }

    techButton.addEventListener("click", function () {
        var div = document.createElement("div");
        content.appendChild(div);
        loadTechnologies();
    });

});
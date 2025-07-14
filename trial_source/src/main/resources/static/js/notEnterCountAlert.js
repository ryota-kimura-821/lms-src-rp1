document.addEventListener('DOMContentLoaded', function () {
    var hasNotEnterCount = document.getElementById('hasNotEnterCount').value;
    if (hasNotEnterCount === "true") {
        alert("未入力の出席情報があります。");
    }
});
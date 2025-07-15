document.addEventListener('DOMContentLoaded', function () {
    const hasNotEnterCount = document.getElementById('hasNotEnterCount').value;
    if (hasNotEnterCount) {
        alert("未入力の出席情報があります。");
    }
});
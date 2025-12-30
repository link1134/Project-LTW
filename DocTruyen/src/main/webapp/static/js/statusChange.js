document.querySelectorAll(".status-select").forEach(select => {
    select.addEventListener("change", function () {
        const chapterId = this.dataset.chapterId;
        const newStatus = this.value;

        fetch(contextPath + "/admin/chapter-manager", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `action=updateStatus&chapterId=${chapterId}&status=${newStatus}`
        })
        .then(res => res.text())
        .then(data => {
            if (data !== "OK") {
                alert("Cập nhật thất bại!");
            }
        })
        .catch(err => {
            console.error(err);
        });
    });
});

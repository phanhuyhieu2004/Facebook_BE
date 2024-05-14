function showSweetAlert() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, block it!'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                'Block!',
                'Record has been successfully block.',
                'success'
            )
        }
    })
}
function myFunction() {
    var body = document.body;
    var root = document.documentElement;

    if (body.dataset.bsTheme === "light") {
        body.dataset.bsTheme = "dark";
        root.classList.add('dark-theme-variables');
    } else {
        body.dataset.bsTheme = "light";
        root.classList.remove('dark-theme-variables');
    }
}
function errorAlert(yes){

    const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
        }
    });
    Toast.fire({
        icon: "error",
        title: "Đăng nhập không thành công"
    });
}


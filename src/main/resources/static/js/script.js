// bytewebster.com
// bytewebster.com
// bytewebster.com
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




document.querySelectorAll('.read-more').forEach(button => {
    button.addEventListener('click', function (e) {
        e.preventDefault();
        const moreContent = this.nextElementSibling;
        moreContent.style.display = moreContent.style.display === 'block' ? 'none' : 'block';
        this.textContent = moreContent.style.display === 'block' ? 'Read Less' : 'Read More';
    });
});

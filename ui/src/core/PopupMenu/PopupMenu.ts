class PopupMenu extends HTMLElement {
    static get is() {
        return 'popup-menu';
    }

    constructor() {
        super();
    }

    get activeClass() {
        return this.getAttribute('active-class') || 'active-menu';
    }

    set active(value: boolean) {
        value ? this.classList.add(this.activeClass) : this.classList.remove(this.activeClass);
        this.setAttribute('aria-hidden', String(!value));
    }

    get active(): boolean {
        return this.classList.contains(this.activeClass);
    }

    public triggerMenu() {
        this.active = !this.active;
    }
}

customElements.define(PopupMenu.is, PopupMenu);

export default PopupMenu;

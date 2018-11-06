import PopupTrigger from "../../../core/PopupMenu/PopupTrigger";

class DropdownInput extends PopupTrigger {
    static get is() {
        return 'dropdown-input';
    }

    constructor() {
        super();
    }

    get menuArr(): HTMLElement[] {
        const els = this.popup.querySelectorAll('[data-menu-item]') as NodeListOf<HTMLElement>;
        return els ? Array.from(els) : [];
    }

    get active(): number {
        return this.menuArr.findIndex((el) => el.classList.contains('active-menu-item'));
    }

    set active(index: number) {
        this.menuArr[this.active].classList.remove('active-menu-item');
        this.menuArr[index].classList.add('active-menu-item');
        this.btn.innerHTML = `Lang: ${this.menuArr[index].innerText}`;
    }

    public connectedCallback() {
        super.connectedCallback();
    }

    public triggerInput(index: number) {
        if (this.active !== index) {
            this.active = index;
        }
    }
}

customElements.define(DropdownInput.is, DropdownInput);

export default DropdownInput;

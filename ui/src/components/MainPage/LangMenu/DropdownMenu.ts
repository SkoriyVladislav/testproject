import PopupMenu from "../../../core/PopupMenu/PopupMenu";
import DropdownInput from "./DropdownInput";

class DropdownMenu extends PopupMenu {
    static get is() {
        return 'dropdown-menu';
    }

    constructor() {
        super();
    }

    get input(): DropdownInput {
        return this.previousElementSibling as DropdownInput;
    }

    public connectedCallback() {
        this.addEventListener('click', (event) => this._onChange(event));
    }

    private _onChange(event: MouseEvent) {
        const target = event.target as HTMLElement;
        this.input.triggerInput(+target.dataset.menuItem - 1);
        this.triggerMenu();
    }
}

customElements.define(DropdownMenu.is, DropdownMenu);

export default DropdownMenu;

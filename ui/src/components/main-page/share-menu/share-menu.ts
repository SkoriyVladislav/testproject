import PopupTrigger from "../../core/popup-menu/popup-trigger";
import PopupMenu from "../../core/popup-menu/popup-menu";

class ShareMenu extends PopupTrigger {

    static get is() { return 'share-menu'; }

    constructor() {
        super();
    }

    get popup(): PopupMenu {
        return this.querySelector('popup-menu') as PopupMenu;
    }
}

customElements.define(ShareMenu.is, ShareMenu);

export default ShareMenu;

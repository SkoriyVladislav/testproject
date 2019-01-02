import PopupTrigger from "../../core/popup-menu/popup-trigger";

class ShareMenu extends PopupTrigger {

    static get is() { return 'share-menu'; }

    constructor() {
        super();
        this.classList.add(ShareMenu.is)
    }

}

customElements.define(ShareMenu.is, ShareMenu);

export default ShareMenu;

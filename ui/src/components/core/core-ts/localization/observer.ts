import DropdownMenu from "../../dropdown-menu/dropdown-menu";
import LabelI18n from "./label-i18n";
import Localization from "./localization";

class Observer {

    constructor() {
        this.menu.addEventListener('dd-menuchanged', (event: CustomEvent) => this._onTrigger(event));
    }

    private _onTrigger(event: CustomEvent) {
        const localization = new Localization();
        localization.currentLocale = event.detail.value;
        const target = event.detail.value as HTMLElement;
        const nextEl = target.querySelector('label-i18n') as LabelI18n;
        window.history.pushState("object or string", "Title", `/new-url`);
    }

    get menu(): DropdownMenu {
        return document.getElementById('dropdown-menu') as DropdownMenu;
    }

}

export default Observer;

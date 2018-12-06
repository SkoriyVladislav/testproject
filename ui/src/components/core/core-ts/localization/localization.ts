import * as paths from '../../../../../paths/config-paths';
import LabelI18n from "./label-i18n";

class Localization {

    currentLocale: string;

    get items() {
        const els = document.querySelectorAll('label-i18n') as NodeListOf<LabelI18n>;
        return els ? Array.from(els) : [];
    }

    private changeLang(trans?: any) {
        this.items.forEach((elem: LabelI18n) => {
            trans && trans[elem.value] ? elem.value = trans[elem.value] : elem.value = elem.enValue;
        })
    }

    private _onChange(event: MouseEvent) {
        // @ts-ignore
        const target = event.detail as HTMLElement;
        const nextEl = target.querySelector('label-i18n') as LabelI18n;
        this.changeLang();
    }
}

export default Localization;
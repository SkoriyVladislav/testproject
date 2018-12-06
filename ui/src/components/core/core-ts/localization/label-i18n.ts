class LabelI18n extends HTMLElement {
    static get is() {
        return 'label-i18n';
    }

    public enValue: string;

    constructor() {
        super();
        this.enValue = this.textContent;
    }

    get value(): string{
        return this.textContent;
    }

    set value(value: string){
        this.textContent = value;
    }
}

customElements.define(LabelI18n.is, LabelI18n);

export default LabelI18n;

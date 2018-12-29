var bundle=function(t){var e={};function s(i){if(e[i])return e[i].exports;var n=e[i]={i:i,l:!1,exports:{}};return t[i].call(n.exports,n,n.exports,s),n.l=!0,n.exports}return s.m=t,s.c=e,s.d=function(t,e,i){s.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:i})},s.r=function(t){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},s.t=function(t,e){if(1&e&&(t=s(t)),8&e)return t;if(4&e&&"object"==typeof t&&t&&t.__esModule)return t;var i=Object.create(null);if(s.r(i),Object.defineProperty(i,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)s.d(i,n,function(e){return t[e]}.bind(null,n));return i},s.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return s.d(e,"a",e),e},s.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},s.p="",s(s.s=4)}([function(t,e){t.exports.INPUT_BUNDLE="src/bundles",t.exports.SRC_DIR="src/components",t.exports.OUTPUT_DIR_PROD="./../content/src/main/content/jcr_root/etc/clientlibs/newsboard",t.exports.AEM_DIR="aem-build",t.exports.OUTPUT_DIR="public",t.exports.INPUT_JS="src/components/**/**/*.js",t.exports.CONTEXT_PATH="../src/bundles",t.exports.TS_CONFIG="../tsconfig.json",t.exports.URL_SERVER="http://localhost:8000/"},function(t,e){let s=new IntersectionObserver(function(t,e){t.forEach(t=>{t.isIntersecting&&(!function(t){"IMG"===t.tagName&&(t.src=t.getAttribute("data-src"),t.onload=(()=>{!function(t){const e=t.closest(".full-post-wrapper");e&&e.querySelector(".full-post-container")&&!e.querySelector(".full-post-container").classList.contains("full-post-margin")&&e.querySelector(".full-post-container").classList.add("full-post-margin")}(t)}))}(t.target),e.unobserve(t.target))})},{rootMargin:"0px 0px 0px 0px",threshold:0});document.querySelectorAll("[data-src]").forEach(t=>{s.observe(t)})},,,function(t,e,s){"use strict";s.r(e);class i extends HTMLElement{static get is(){return"popup-menu"}constructor(){super()}connectedCallback(){this.classList.add("popup-menu")}get type(){return this.getAttribute("type")||"non-modal"}get activeClass(){return this.getAttribute("active-class")||"active-menu"}set active(t){"modal"===this.type&&this.classList.toggle("modal-popup"),t?this.classList.add(this.activeClass):this.classList.remove(this.activeClass),this.setAttribute("aria-hidden",String(!t))}get active(){return this.classList.contains(this.activeClass)}}customElements.define(i.is,i);var n=i;class r extends HTMLElement{static get is(){return"popup-trigger"}get btn(){return this.querySelector("[data-popup-btn]")}get popup(){const t=this.getAttribute("target");return document.getElementById(t)}constructor(){super(),this._onActivate=this._onActivate.bind(this)}connectedCallback(){this.classList.add("popup-trigger"),this._attachEvent()}disconnectedCallback(){this._detachEvent()}get triggerOn(){return this.dataset.triggeron||"click"}_attachEvent(){"click"===this.triggerOn?this.addEventListener("click",this._onActivate,!1):this.addEventListener(this.triggerOn,this._onActivate,!1)}_detachEvent(){"click"===this.triggerOn?this.removeEventListener("click",this._onActivate):this.removeEventListener(this.triggerOn,this._onActivate)}_onActivate(){this.popup.active=!this.popup.active}}customElements.define("popup-trigger",r);var a=r;class c extends HTMLElement{static get is(){return"slide-carousel"}constructor(){super()}get activeClass(){return this.getAttribute("active-slide-class")||"active-slide"}get activeIndex(){return this.slides.findIndex(t=>t.classList.contains(this.activeClass))}_cleanAnimationClasses(){this.slides.forEach(t=>{t.classList.remove("left"),t.classList.remove("right")})}set activeIndex(t){const e=t;t=(t+this.slides.length)%this.slides.length,this._cleanAnimationClasses(),e>this.activeIndex?this.slides[t].classList.add("left"):this.slides[t].classList.add("right"),this.slides[this.activeIndex].classList.remove(this.activeClass),this.slides[t].classList.add(this.activeClass),this.triggerSlideChange()}get slides(){const t=this.querySelectorAll("[data-slide-item]");return t?Array.from(t):[]}connectedCallback(){this.classList.add("slide-carousel"),this.bindEvents()}bindEvents(){this.addEventListener("click",t=>this._onClick(t),!1)}_onClick(t){const e=t.target;e&&e.dataset.slideTarget&&this.setActive(e.dataset.slideTarget)}setActive(t){"prev"===t?this.activeIndex--:"next"===t?this.activeIndex++:this.activeIndex=+t}triggerSlideChange(){const t=new CustomEvent("sc-slidechanged",{bubbles:!0});this.dispatchEvent(t)}}customElements.define(c.is,c);var o=c;class l extends HTMLElement{constructor(){super(),this._onUpdate=(()=>this.rerender())}connectedCallback(){this.classList.add("slide-carousel-dots"),this._parent=this.closest(o.is),this.rerender(),this._parent.addEventListener("sc-slidechanged",this._onUpdate)}disconnectedCallback(){this._parent.removeEventListener("sc-slidechanged",this._onUpdate)}rerender(){let t="";const e=this._parent.slides.length,s=this._parent.activeIndex;for(let i=0;i<e;++i)t+=this.buildDot(i,i===s);this.innerHTML=t}buildDot(t,e){return`<span class="carousel-dot ${e?"active-dot":""}" data-slide-target="${t}"> </span>`}}customElements.define("slide-carousel-dots",l);class u extends a{static get is(){return"dropdown-input"}constructor(){super()}get defaultValue(){return this.dataset.defaultValue}get value(){const t=this.dataset.inputValue.replace(/<\/?[^>]+(>|$)/g,"").replace("$","");return this.innerText.replace(t,"")}set value(t){this.btn.innerHTML=this.dataset.inputValue.replace("$",t)}connectedCallback(){this.classList.add("dropdown-input"),this.value=this.defaultValue,super.connectedCallback()}triggerInput(t){this.value!==t&&(this.value=t,this.triggerValueChange(t))}triggerValueChange(t){const e=new CustomEvent("dd-inputchanged",{bubbles:!0,detail:{value:t,elem:this}});this.dispatchEvent(e)}}customElements.define(u.is,u);class d extends n{static get is(){return"dropdown-menu"}constructor(){super()}connectedCallback(){super.connectedCallback(),this.classList.add("dropdown-menu"),this.menuArr[0].setAttribute("active-menu-item",""),this.addEventListener("click",t=>this._onChange(t))}disconnectedCallback(){this.removeEventListener("click",t=>this._onChange(t))}get input(){return document.getElementById("dropdown-input")}get menuArr(){const t=this.querySelectorAll("[data-menu-item]");return t?Array.from(t):[]}get activeItm(){return this.querySelector("[active-menu-item]")}set activeIndex(t){this.activeItm.toggleAttribute("active-menu-item"),this.menuArr[t].toggleAttribute("active-menu-item")}_onChange(t){const e=t.target,s=e.querySelector("label-i18n");this.activeIndex=+e.dataset.menuItem-1,this.input.triggerInput(s.key),this.active=!this.active,this.active||this.activeItm&&this.activeItm.focus()}}customElements.define(d.is,d);s(1);var h=s(0),p=function(t,e,s,i){return new(s||(s=Promise))(function(n,r){function a(t){try{o(i.next(t))}catch(t){r(t)}}function c(t){try{o(i.throw(t))}catch(t){r(t)}}function o(t){t.done?n(t.value):new s(function(e){e(t.value)}).then(a,c)}o((i=i.apply(t,e||[])).next())})};var v={sendRequest(t){return p(this,void 0,void 0,function*(){return yield fetch(`${h.URL_SERVER}assets/i18n/${t}.json`,{method:"GET",mode:"cors",headers:{"Content-Type":"application/json; charset=utf-8"}}).then(t=>{try{return t.json()}catch(t){return Promise.reject(t)}}).catch(t=>{console.log(t)})})}};let g;class m{constructor(){if(g)return g;(g=this)._subscribers=[]}onChange(){v.sendRequest(this._currentLocale).then(t=>{this._translations=t,this.emitChanges()})}emitChanges(){this._subscribers.forEach(t=>t.locationChange(this))}getLocalizedValue(t){return this._translations[t]?this._translations[t]:t}static subscribe(t){(new m).subscribe(t)}static unsubscribe(t){(new m).unsubscribe(t)}subscribe(t){this._subscribers.push(t)}unsubscribe(t){const e=this._subscribers.indexOf(t);this._subscribers.splice(e,1)}static instance(){return new m}setLocale(t){this._currentLocale=t,this.onChange()}}window.LocalizationManager=m;var b=m;class f extends HTMLElement{static get is(){return"label-i18n"}connectedCallback(){b.subscribe(this)}disconnectedCallback(){b.unsubscribe(this)}constructor(){super(),this.key=this.textContent}get value(){return this.textContent}locationChange(t){this.textContent=t.getLocalizedValue(this.key)}}customElements.define(f.is,f);let _;class E{constructor(){if(_)return _;(_=this)._popup=_._createElement(),document.querySelector("body").appendChild(_._popup)}get iframe(){return this._popup.querySelector("iframe")}static show(t){const e=new E;e._url=t,e.show()}show(){this._popup&&!this._popup.active&&(this.iframe.src=this._url,this._popup.active=!0)}static hide(){(new E).hide()}hide(){this._popup&&this._popup.active&&(this.iframe.src="",this._popup.active=!1)}_createElement(){const t=document.createElement("template");return t.innerHTML='<popup-menu id="video-popup" data-popup-content type="modal">\n                <div class="video-container">\n                    <iframe class="video-iframe" frameborder="0" allowfullscreen></iframe>\n                    <btn class="hide-video-btn">\n                        <svg width="40" height="40" class="hide-video-svg">\n                            <line x1="10" y1="10" x2="30" y2="30"/>\n                            <line x1="30" y1="10" x2="10" y2="30"/>\n                        </svg>\n                    </btn>\n                </div>\n            </popup-menu>',t.content.firstElementChild}}var y=E;var L=class{constructor(t){t.addEventListener("click",t=>this._onShow(t),!1),this.btnHide.addEventListener("click",()=>y.hide())}get btnHide(){return document.querySelector(".hide-video-btn")}_onShow(t){t.preventDefault();const e=t.target.getAttribute("href");y.show(e)}};var C=class{constructor(){this.btnHide.addEventListener("click",()=>y.hide())}get btnHide(){return document.querySelector(".hide-video-btn")}};[y,C,[class{constructor(t){this._manager=b.instance(),this._dropdown=t.querySelector("dropdown-input"),this.bindEvents()}bindEvents(){this._dropdown.addEventListener("dd-inputchanged",t=>{this._manager.setLocale(t.detail.value)})}},".popup-menu-container"]].forEach(t=>{let e,s="";Array.isArray(t)?[e,s]=t:e=t,new e(s&&document.querySelector(s))}),document.querySelectorAll('.post a[href*="youtube"]').forEach(t=>new L(t))}]);
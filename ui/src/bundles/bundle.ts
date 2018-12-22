import '../components/core/popup-menu/popup-menu';
import '../components/core/popup-menu/popup-trigger';
import '../components/core/slide-carousel/slide-carousel';
import '../components/core/slide-carousel/slide-carousel-dots';
import '../components/core/dropdown-menu/dropdown-input';
import '../components/core/dropdown-menu/dropdown-menu';
import '../components/core/core-ts/lazyload'
import '../components/core/core-ts/localization/label-i18n'

import VideoService from '../components/core/video-overlay/video-service';
import VideoTrigger from '../components/core/video-overlay/video-trigger';
import VideoOverlay from "../components/main-page/video-overlay/video-overlay";
import LangMenuController from "../components/main-page/lang-menu/lang-menu";

const COMPONENTS_LIST = [
    VideoService,
    VideoOverlay,
    [LangMenuController, '.popup-menu-container']
];

COMPONENTS_LIST.forEach((component: Function | Array<any>) => {
    let Component;
    let selector = '';

    if (Array.isArray(component)) {
        [Component, selector] = component;
    } else {
        Component = component;
    }

    // console.log(`[Component Initialization]: component ${Component.name} on "${selector}"`);
    new Component(selector && document.querySelector(selector));
});

const links = document.querySelectorAll('.post a[href*="youtube"]');
links.forEach((link: HTMLElement) => new VideoTrigger(link));

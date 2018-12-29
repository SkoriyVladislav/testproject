import VideoService from "./video-service";

class VideoTrigger {

    constructor(link: HTMLElement) {
        link.addEventListener('click', (event) => this._onShow(event), false);
    }

    private _onShow(event: MouseEvent) {
        event.preventDefault();
        const target = event.target as HTMLElement;
        const url = target.getAttribute('href');
        VideoService.show(url);
    }
}

export default VideoTrigger;

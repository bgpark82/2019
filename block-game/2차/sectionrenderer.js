const SectionRenderer = class extends Renderer {
    constructor({stage, bg, w, h, c, r, img, itemFactory}){
        // Renderer에게 itemFactory 줘야 
        super(itemFactory);
        stage = el(stage);
        const bw = parseInt(w/c);
        const bh = parseInt(h/r);
        // loop
        const _q = [];

        prop(this, {stage, bw, bh, w, h, c,r, img, isdown:false, _q,isAct:null, curr:0});

        stage.style.cssText = `width:${w}px; height:${h}px;background-image:url('${bg});background-size:${bw}px ${bh}px`
        // drag시 select를 막으려면 두개의 속성 모두 들어가있어야 한다.
        stage.setAttribute('unselectable','on');
        stage.setAttribute('onselectstart','return false');
        // 무한루프 = 애니메이션 루프 = render 루프 = 거의 모든 게임엔진 = 계속 화면 갱신
        // 메인 루프 하나에 q 놔두고 계속 돌림
        const f = t => {
            // 현재의 시간을 항상 t로 변환
            this.curr = t;
            // _q가 있으면 안의 내용 검사 
            for(let i = _q.length; i--;){
                const task = _q[i];
                // _q에 정해져 있는 시간이 현재 시간보다 작다며 = 5초라고 설정해놓고 6초가 지나면 실행하고 빼내기
                if(task.t <= t){
                    _q.splice(i, 1);
                    task.f();
                }
            }
            this._renderLoop();
            requestAnimationFrame(f);
        };
        requestAnimationFrame(f);
    }

    delayTask(f, time){
        this._q.push({f, t:this.curr + time});
    }
    activate() {
        const {stage} = this;
        if(this.isAct === null){
            stage.addEventListener('mousedown', e => this.isAct && this.dragDown(e));
            stage.addEventListener('mouseup', e => this.isAct && this.dragUp(e));
            stage.addEventListener('mouseleave', e => this.isAct && this.dragUp(e));
            stage.addEventListener('mousemove', e => this.isAct && this.dragMove(e));
        }
        this.isAct = true;
    }
    deactivate() {
        this.isAct = false;
    }
    _add(item) { this.stage.appendChild(item.object)}
    _remove(item) {this.stage.removeChild(item.object)}
    _render(){}
    _getItem(x, y){
        const el = document.elementFromPoint(x, y);
        return this.some(v=>v.find(el));
    }
    dragDown({pageX:x, pageY:y}){
        const item = this._getItem(x,y);
        if(!item) return;
        this.isdown = true;
        this.itemStart(item);
    }
    dragMove({pageX:x, pageY:y}){
        const {isdown} = this;
        if(!isdown) return;
        const item = this._getItem(x,y);
        if(item) this.itemNext(item);
    }
    dragUp({pageX:x, pageY:y}){
        const {isdown} = this;
        if(!isdown) return;
        this.isdown = false;
        this.itemEnd();
    }
}
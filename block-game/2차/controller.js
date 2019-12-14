// controller
const Game = class {
    constructor(setting){
      prop(this, setting, {
        items:new WeakSet(),
        msg2item:new WeakMap(),
        item2msg:new WeakMap(),
        prevItem:null
      })
  
      const {renderer, row,column, items, item2msg} = this;
      renderer.setGame(this, row, column);
      for(let c = 0; c < column; c++){
        for(let r = 0; r < row; r++) this._add(c,r)
      }
  
      Promise.all(items.map(item =>{
      item.pos(item.x, item.y + row);                                      // 위에서 row만큼의 높이에서 떨어짐, row만큼 띄움
        return renderer.move(item2msg.get(item).pos(item.x, item.y));
      })).then(_=> renderer.activate())
    }
  
    // 모델 + 메세지 만들기
    _add(c, r){
      const {itemType, row, items, msg2item, item2msg, renderer} = this;
      // 떨어질 블록 row보다 위에서 준비
      const item = new Item(itemType[parseInt(Math.random() * itemType.length)], c, r - row);
      // const msg = new Game();
      const msg = {};
      items.add(item);
      item2msg.set(item,msg);
      renderer.add(msg);
      return item;
    }
  
    _delete(item){
      const msg = this.item2msg.get(item);
      this.msg2item.delete(msg);
      this.item2msg.delete(item);
      this.items.delete(item);
    }
  
    getInfo(msg){
      const item = this.msg2item.get(msg);
      msg.info(item.x, item.y, item.type, item.selected);
      return msg;
    }
  
    selectStart(msg){
      const item = this.msg2item.get(msg);
      if(!item) return;
      item.select();
      this.prevItem = item;
    }
  
    selectNext(msg){
      const item = this.msg2item.get(msg);
      if(!item) return;
  
      const {prevItem:curr} = this;                                               // prevItem을 curr로 바꿈
      // 자신이 아니고 || 같은 타입(같은 색) || 인접셀
      if(item == curr || item.type != curr.type || !curr.isBorder(item)) return;
      // 위의 조건을 만족하면 = 인접셀이면서 || next일 자격 갖춤
      // 선택된 리스트에 없으면 add = prev값이 있는지 재귀적으로 찾음
      if(!curr.isSelectedList(item)){
        item.select(curr);
        this.prevItem = item;
      // 선택된 리스트 중에 직전 것이면 release
      }else {
        if(curr.prev === item){
          this.prevItem = curr.prev;
          curr.unselect();
        }
      }
    }
  
    selectEnd() {
      const {items, item2msg, renderer} = this;
      // 매번 링크드리스트로 찾아가기 귀찮으니 로컬에서 캐시하려고 잠깐 만듦
      const selected = [];
  
      // select가 되어있으면 push로 넣기
      items.forEach(v => v.selected && selected.push(item2msg.get(v)));
      // remove 먼저해라 그러고 내가 clear할께 
      if(selected.length > 2) renderer.remove(selected).then(_=>this._clear());
      else items.forEach(v => v.unselect());
      this.prevItem = null;
    }
  
    _clear(selectedItems){
      const {items, renderer} = this;
      renderer.deactivate();
      items.forEach(item => item.selected && this._delete(item));
      this._dropBlocks();
    }
  
    _dropBlocks() {
      const {items, column, row, renderer, item2msg} = this;
  
      // 모든 아이템들을 담을 2차원 배열에 아이템 다 밀어 넣음 -> 빨리 처리하기위해
      const allItems = [];
      for(let i = row; i--;) allItems.push([]);
      items.forEach(item => (allItems[item.y][item.x] = item));
  
      // column, row 돌면서 밑에 줄 부터 빈공간이 있는지 파악
      // Promise를 수집하는 coll
      const coll = [];
      for(let c = 0; c < column; c++){
        for(let r = row - 1; r > -1; r--){
          if(allItems[r] && allItems[r][c]){
            let cnt = 0;
            for(let j = r+1; j < row; j++){
              // 빈공간 있으면 count
              if(allItems[j] && !allItems[j][c]) cnt++;
            }
            // 빈공간 있으면 컬렉션에 담음
            if(cnt){
              const item = allItems[r][c];
              item.pos(c, r+cnt);
              // renderer한테 떨어질 공간만큼 밑으로 move를 요청
              coll.push(renderer.move(item2msg.get(item).pos(item.x, item.y)))
            }
          }
        }
      }
      // Promise를 수집해서 떨어질 애가 있으면 Promise.all
      if(coll.length) Promise.all(coll).then(_=> this._fillStart())
    }
  
    _fillStart() {
      const {items, column, row, renderer, item2msg} = this;
  
      const allItems =[];
      for(let i = row; i--;) allItems.push([]);
      items.forEach(item => (allItems[item.y][item.x] = item))
  
      const coll = [];
      for(let c = 0; c < column; c++){
        for(let r = row -1; r > -1; r--){
          if(allItems[r] && ! allItems[r][c]){
            coll.push(this._add(c,r))
          }
        }
      }
      if(!coll.length) return;
      Promise.all(comm.map(item=>{
        item.pos(item.x, item.y + row);
        return renderer.move(item2msg.get(item).pos(item.x, item.y));
        // 새로만든 애들이 떨어져야 이벤트가 activate
      }).then(_=>renderer.activate()))
    }
  }
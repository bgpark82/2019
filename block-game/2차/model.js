// model
const Item = class {
    constructor(_type, _x, _y) {
      prop(this, { _type, _x, _y, _selected: false, _prev: null });
    }
  
    static GET(type, x, y) {
      return new Item(type, x, y);
    }
  
    get type() {return this._type;}
    get x() {return this._x;}
    get y() {return this._y;}
    get selected() {return this._selected;}
    get prev() {return this._prev;}
    pos(x,y){
      this._x = x;
      this._y = y;
    }
    select(item){
      this._selected = true;
      this._prev=item;
    }
    unselect(){
      this._selected = false;
      this._prev=null;
    }
    isSelectedList(item){                                                       // 본인에게 연결된 아이템이 있는지 판별
      if(!this._prev) return false;                                             // prev 없으면 false
      if(this._prev === item) return true                                       // prev 있으면 true
      else return this._prev.isSelectedList(item);
    }
    isBorder(item){
      return Math.abs(this.x - item.x) < 2 && Math.abs(this.y - item.y) < 2;    // 이전 블록과 가까이 붙어있으면 truedd
    }
  };

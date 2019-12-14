const ItemRenderer = class {
    get Object() {throw 'override!'}
    find(v) {throw 'override!'}
    remove() {return this._remove()}
    move(x, y) {return this._move(x,y)}
    render(x, y, type, selected){this._render(x, y, type, selected)}
    _remove(){throw 'override'}
    _move(x, y){throw 'override'}
    _render(x, y, type, selected){throw 'override'}
}
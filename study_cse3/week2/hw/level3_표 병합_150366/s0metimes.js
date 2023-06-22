function solution(commands) {
    var answer = [];
    
    const table = Table(60, 60);
    
    for(let command of commands) {
        const arr = command.split(" ");
        const commandKey = arr[0];
        
        switch(commandKey) {
            case "UPDATE":
                update(arr, table);
                break;
            case "MERGE":
                merge(arr, table);
                break;
            case "UNMERGE":
                unmerge(arr, table);
                break;
            case "PRINT":
                print(arr, table, answer);
                break;
        }
    }
    
    return answer;
}


function update(arr, table) {
    if(arr.length === 4) {
        const r = parseInt(arr[1]);
        const c = parseInt(arr[2]);
        const value = arr[3];
        
        table.setValue(r, c, value);
    }
    else {
        const value1 = arr[1];
        const value2 = arr[2];
        
        table.changeValues(value1, value2);
    }    
}

function merge(arr, table) {
    const r1 = parseInt(arr[1]);
    const c1 = parseInt(arr[2]);
    const r2 = parseInt(arr[3]);
    const c2 = parseInt(arr[4]);
    
    table.merge(r1, c1, r2, c2);
}

function unmerge(arr, table) {
    const r = parseInt(arr[1]);
    const c = parseInt(arr[2]);
    
    table.unmerge(r, c);
}

function print(arr, table, answer) {
    const r = parseInt(arr[1]);
    const c = parseInt(arr[2]);
    
    answer.push(table.getValue(r, c));
}

function Table(numOfRows, numOfColumns) {
    const _t = {};
    const table = [];
    for(let i = 0; i < numOfRows; i++) {
        table.push([]);
        for(let j = 0; j < numOfColumns; j++) {
            table[i][j] = TableCell();
        }
    }
    
    _t.table = table;
    
    _t.setValue = function(r, c, value) {
        const cell = _t.table[r-1][c-1];
        cell.setValue(value);
    } 
    
    _t.changeValues = function(value1, value2) {
        for(let row of _t.table) {
            for(let cell of row) {
                cell.isVisited = false;
            }
        }
        
        for(let row of _t.table) {
            for(let cell of row) {
                cell.changeValue(value1, value2);
            }
        }
    }
    
    _t.getValue = function(r, c) {
        const cell = _t.table[r-1][c-1];
        const value = cell.getValue();
        
        if(value === "") {
            return "EMPTY";
        }
        else {
            return value;
        }
    }
    
    _t.merge = function(r1, c1, r2, c2) {
        const cell1 = _t.table[r1-1][c1-1];
        const cell2 = _t.table[r2-1][c2-1];
        
        cell1.merge(cell2);
    }
    
    _t.unmerge = function(r, c) {
        const cell = _t.table[r-1][c-1];
        cell.unmerge();
    }
    
    return _t;
}

function TableCellGroup() {
    const _group = {};
    
    _group.value = "";
    _group.children = [];

    _group.getValue = function() {
        return _group.value;
    }

    _group.setValue = function(value) {
        _group.value = value;
    }
    
    _group.addChild = function(cell) {
        cell.group = _group;
        _group.children.push(cell);
    }

    _group.merge = function(group) {
        for(let child of group.children) {
            child.group = _group;
            _group.children.push(child);
        }
    }

    _group.unmerge = function() {
        for(let child of _group.children) {
            child.group = undefined;
            child.setValue("");
        }
    }

    _group.changeValue = function(value1, value2) {
        if(_group.value === value1) {
            _group.value = value2;
        }

        for(let child of _group.children) {
            child.isVisited = true;
        }
    }
    
    return _group;
}

function TableCell() {
    const _cell = {};
    
    _cell.value = "";
    _cell.group = undefined;
    _cell.isVisited = false;
    
    _cell.merge = function(cell) {
        if(_cell === cell) {
            return;
        }

        if(_cell.group && cell.group && _cell.group === cell.group) {
            return;
        }

        const value1 = _cell.getValue();
        const value2 = cell.getValue();

        let parentCell;
        let childCell;

        if(value1 === "" && value2 !== "") {
            parentCell = cell;
            childCell = _cell;
        }
        else {
            parentCell = _cell;
            childCell = cell;
        }

        if(!parentCell.group) {
            parentCell.group = TableCellGroup();
            parentCell.group.setValue(parentCell.value);
            parentCell.group.addChild(parentCell);
        }
        
        if(childCell.group) {
            parentCell.group.merge(childCell.group);
        }
        else {
            parentCell.group.addChild(childCell);
        }
    }
    
    _cell.unmerge = function() {
        const value = _cell.getValue();
        if(_cell.group) {
            _cell.group.unmerge();
        }

        _cell.value = value;
    }
    
    _cell.changeValue = function(value1, value2) {
        if(_cell.group) {
            _cell.group.changeValue(value1, value2);
        }
        else {
            if(_cell.value === value1) {
                _cell.value = value2;
            }

            _cell.isVisited = true;
        }
    }
    
    _cell.getValue = function() {
        if(_cell.group) {
            return _cell.group.getValue();
        }
        else {
            return _cell.value;
        }
    }
    
    _cell.setValue = function(value) {
        if(_cell.group) {
            _cell.group.setValue(value);
        }
        else {
            _cell.value = value;
        }
    }
    
    return _cell;
}
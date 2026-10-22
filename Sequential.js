let sequentialSearch = (array, find_number) => {
    let pos = "Not found";
    for(let i = 0; i < array.length; i++) {
        if (array[i] == find_number) {
            pos = i;
        }
    }
    return `${find_number} is Position ${pos}`;
}

console.log(sequentialSearch([10, 20, 30, 40, 50], 40));
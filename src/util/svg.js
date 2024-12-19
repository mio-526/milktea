const modules = import.meta.glob('@/assets/svg/*.svg')
let icons = []
for(const path in modules){
    const p  = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.'))
    icons.push(p)
}

export default icons

//设置缓存
function set_cache(key,value)
{
    if(key=='') return false;
    localStorage.setItem(key, value);
}

//读取缓存
function get_cache(key)
{
    return localStorage.getItem(key);
}

//删除缓存
function remove_cache(key)
{
    localStorage.removeItem(key);
}

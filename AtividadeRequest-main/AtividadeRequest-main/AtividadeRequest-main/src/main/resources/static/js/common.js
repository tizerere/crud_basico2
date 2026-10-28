async function api(url, options={}){const r=await fetch(url,{headers:{'Content-Type':'application/json',...(options.headers||{})},...options});if(!r.ok){throw new Error(`Erro ${r.status}`)}if(r.status===204)return null;return r.json()}
function msg(text,ok=true){const el=document.querySelector('#msg');if(el){el.textContent=text;el.className='msg '+(ok?'ok':'error')}}

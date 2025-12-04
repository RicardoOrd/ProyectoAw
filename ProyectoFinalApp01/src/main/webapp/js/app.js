document.addEventListener("DOMContentLoaded", () => {
    // 1. Detectar en qué vista estamos buscando elementos clave
    const tabla = document.getElementById('tabla-body');
    const formRegistro = document.getElementById('formRegistro');
    // Eliminado: const formLogin...
    const nombreUsuarioSpan = document.getElementById('nombre-usuario'); 

    // 2. Ejecutar la lógica correspondiente según la página
    if (tabla) {
        cargarParticipantes();
    }

    if (formRegistro) {
        inicializarFormulario();
    }

    // Eliminado: if (formLogin) ...
    
    if (nombreUsuarioSpan) {
        cargarNombreBienvenida();
    }
});

// ==========================================
// 1. LÓGICA DEL LISTADO (listado.jsp)
// ==========================================

async function cargarParticipantes() {
    try {
        const response = await fetch('api/participantes');
        


        const datos = await response.json();
        const tbody = document.getElementById('tabla-body');
        tbody.innerHTML = ""; // Limpiar tabla antes de pintar

        datos.forEach(p => {
            const tr = document.createElement('tr');
            // Construcción dinámica de filas
            tr.innerHTML = `
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.email}</td>
                <td>${p.experiencia || ''}</td>
                <td>
                    <button class="btn-eliminar" style="color:red; cursor:pointer;" onclick="eliminar(${p.id})">Borrar</button>
                    <button class="btn-editar" style="color:blue; cursor:pointer;" onclick="irAEditar(${p.id})">Editar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error("Error cargando tabla:", error);
        const tbody = document.getElementById('tabla-body');
        tbody.innerHTML = "<tr><td colspan='5'>Error cargando datos. Revise la consola.</td></tr>";
    }
}

// Funciones globales para los botones de la tabla
function irAEditar(id) {
    window.location.href = `editar.jsp?id=${id}`;
}

async function eliminar(id) {
    if(confirm("¿Seguro que deseas eliminar este registro?")) {
        try {
            const resp = await fetch(`api/participantes/${id}`, { method: 'DELETE' });
            if (resp.ok) {
                cargarParticipantes(); // Recargar tabla visualmente
            } else {
                alert("No se pudo eliminar el registro");
            }
        } catch (error) {
            console.error(error);
        }
    }
}

// ==========================================
// 2. LÓGICA DEL FORMULARIO DE REGISTRO/EDICIÓN
// ==========================================

async function inicializarFormulario() {
    // A. Revisar si hay un ID en la URL (Modo Edición)
    const urlParams = new URLSearchParams(window.location.search);
    const idEditar = urlParams.get('id');

    if (idEditar) {
        document.title = "Editar Participante";
        await cargarDatosParaEditar(idEditar);
    }

    // B. Manejar el envío del formulario (CREATE / UPDATE)
    document.getElementById('formRegistro').addEventListener('submit', async (e) => {
        e.preventDefault(); 
        
        const datos = {
            nombre: document.getElementById('nombre').value,
            email: document.getElementById('email').value,
            fechaNacimiento: document.getElementById('fechaNacimiento').value,
            experiencia: document.getElementById('experiencia').value,
            aceptoTerminos: document.getElementById('aceptoTerminos').checked, 
            comentarios: document.getElementById('comentarios').value,
            intereses: Array.from(document.getElementById('intereses').selectedOptions).map(opt => opt.value)
        };

        let url = 'api/participantes';
        let method = 'POST';

        if (idEditar) {
            url += `/${idEditar}`; 
            method = 'PUT';
            datos.id = idEditar; 
        }

        try {
            const resp = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datos)
            });

            if (resp.ok) {
                if (idEditar) {
                    alert("Actualizado correctamente");
                    window.location.href = "listado.jsp";
                } else {
                    const nombreEncoded = encodeURIComponent(datos.nombre);
                    window.location.href = `registroExitoso.jsp?nombre=${nombreEncoded}`;
                }
            } else {
                alert("Error al guardar en el servidor (Código " + resp.status + ")");
            }
        } catch (error) {
            console.error(error);
            alert("Error de conexión con la API");
        }
    });
}

async function cargarDatosParaEditar(id) {
    try {
        const resp = await fetch(`api/participantes/${id}`);
        if (!resp.ok) return;
        
        const p = await resp.json();

        document.getElementById('nombre').value = p.nombre;
        document.getElementById('email').value = p.email;
        document.getElementById('fechaNacimiento').value = p.fechaNacimiento || '';
        document.getElementById('experiencia').value = p.experiencia;
        document.getElementById('comentarios').value = p.comentarios;
        document.getElementById('aceptoTerminos').checked = p.aceptoTerminos;
        
        const hiddenId = document.getElementById('idParticipante');
        if(hiddenId) hiddenId.value = p.id;

        if (p.intereses) {
            const selectIntereses = document.getElementById('intereses');
            Array.from(selectIntereses.options).forEach(opt => {
                if (Array.isArray(p.intereses) && p.intereses.includes(opt.value)) {
                    opt.selected = true;
                } else if (typeof p.intereses === 'string' && p.intereses.includes(opt.value)) {
                    opt.selected = true; 
                }
            });
        }
        
        const btn = document.querySelector('button[type="submit"]');
        if(btn) btn.textContent = "Actualizar Cambios";

    } catch (error) {
        console.error("Error cargando datos:", error);
    }
}

// ==========================================
// 4. LÓGICA DE BIENVENIDA (registroExitoso.jsp)
// ==========================================

function cargarNombreBienvenida() {
    const params = new URLSearchParams(window.location.search);
    const nombre = params.get('nombre');
    
    if (nombre) {
        document.getElementById('nombre-usuario').textContent = nombre;
    }
}
document.addEventListener("DOMContentLoaded", () => {
    // Detectar en qué página estamos
    const tabla = document.getElementById('tabla-body');
    const form = document.getElementById('formRegistro');

    if (tabla) {
        cargarParticipantes();
    }

    if (form) {
        inicializarFormulario();
    }
});

// --- LÓGICA DEL LISTADO (listado.jsp) ---

async function cargarParticipantes() {
    try {
        const response = await fetch('api/participantes');
        const datos = await response.json();
        
        const tbody = document.getElementById('tabla-body');
        tbody.innerHTML = ""; 

        datos.forEach(p => {
            const tr = document.createElement('tr');
            // AQUÍ QUITAMOS 'APELLIDOS' Y USAMOS TUS CAMPOS REALES
            tr.innerHTML = `
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.email}</td>
                <td>${p.experiencia || ''}</td>
                <td>
                    <button onclick="eliminar(${p.id})">Borrar</button>
                    <button onclick="irAEditar(${p.id})">Editar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error("Error cargando tabla:", error);
    }
}

function irAEditar(id) {
    // ANTES: window.location.href = `index.jsp?id=${id}`;
    // AHORA: Redirige a la página dedicada de edición
    window.location.href = `editar.jsp?id=${id}`;
}

async function eliminar(id) {
    if(confirm("¿Seguro que deseas eliminar este registro?")) {
        await fetch(`api/participantes/${id}`, { method: 'DELETE' });
        cargarParticipantes(); // Recargar tabla
    }
}

// --- LÓGICA DEL FORMULARIO (index.jsp) ---

async function inicializarFormulario() {
    // 1. Revisar si hay un ID en la URL (Modo Edición)
    const urlParams = new URLSearchParams(window.location.search);
    const idEditar = urlParams.get('id');

    if (idEditar) {
        document.title = "Editar Participante"; // Cambiar título pestaña
        await cargarDatosParaEditar(idEditar);
    }

    // 2. Manejar el envío del formulario
    document.getElementById('formRegistro').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const datos = {
            nombre: document.getElementById('nombre').value,
            email: document.getElementById('email').value,
            fechaNacimiento: document.getElementById('fechaNacimiento').value,
            experiencia: document.getElementById('experiencia').value,
            // Convertir checkbox a boolean
            aceptoTerminos: document.getElementById('aceptoTerminos').checked, 
            comentarios: document.getElementById('comentarios').value,
            // Convertir select múltiple a array de strings
            intereses: Array.from(document.getElementById('intereses').selectedOptions).map(opt => opt.value)
        };

        let url = 'api/participantes';
        let method = 'POST';

        // Si estamos editando, cambiamos a PUT y añadimos el ID
        if (idEditar) {
            url += `/${idEditar}`; // api/participantes/5
            method = 'PUT';
            datos.id = idEditar; // Añadir ID al JSON
        }

        try {
            const resp = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datos)
            });

            if (resp.ok) {
                alert(idEditar ? "Actualizado correctamente" : "Registrado correctamente");
                window.location.href = "listado.jsp";
            } else {
                alert("Error al guardar");
            }
        } catch (error) {
            console.error(error);
        }
    });
}

async function cargarDatosParaEditar(id) {
    try {
        const resp = await fetch(`api/participantes/${id}`);
        if (!resp.ok) return;
        
        const p = await resp.json();

        // Rellenar los campos del formulario con los datos recibidos
        document.getElementById('nombre').value = p.nombre;
        document.getElementById('email').value = p.email;
        document.getElementById('fechaNacimiento').value = p.fechaNacimiento || '';
        document.getElementById('experiencia').value = p.experiencia;
        document.getElementById('comentarios').value = p.comentarios;
        document.getElementById('aceptoTerminos').checked = p.aceptoTerminos;

        // Seleccionar los intereses (Select Multiple)
        if (p.intereses) {
            const selectIntereses = document.getElementById('intereses');
            Array.from(selectIntereses.options).forEach(opt => {
                if (p.intereses.includes(opt.value)) {
                    opt.selected = true;
                }
            });
        }
        
        // Cambiar texto del botón para que se vea claro
        const btn = document.querySelector('button[type="submit"]');
        if(btn) btn.textContent = "Actualizar Cambios";

    } catch (error) {
        console.error("Error cargando datos para editar:", error);
    }
}       
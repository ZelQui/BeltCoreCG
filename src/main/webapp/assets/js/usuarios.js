const userModal = document.getElementById("userModal");
const closeModal = userModal.querySelector(".close");

document.querySelector(".add-provider").addEventListener("click", () => {
    userModal.style.display = "block";
});

closeModal.addEventListener("click", () => {
    userModal.style.display = "none";
});

window.addEventListener("click", (e) => {
    if (e.target === userModal) userModal.style.display = "none";
});

document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("userModal");
    const form = document.getElementById("userForm");
    const closeBtn = modal.querySelector(".close");

    // Función para abrir el modal y rellenar si es edición
    function openModal(mode, userData = {}) {
        form.reset();
        form.setAttribute("data-mode", mode);

        if (mode === "edit") {
            document.getElementById("firstName").value = userData.firstName || "";
            document.getElementById("lastName").value = userData.lastName || "";
            document.getElementById("email").value = userData.email || "";
            document.getElementById("username").value = userData.username || "";
            document.getElementById("role").value = userData.role || "";
            document.getElementById("status").value = userData.status || "";
        }

        modal.style.display = "block";
    }

    // Cerrar modal
    closeBtn.onclick = () => modal.style.display = "none";
    window.onclick = (e) => { if (e.target === modal) modal.style.display = "none"; };

    // Acción: Ver usuario
    document.querySelectorAll(".btn.view").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const userName = row.children[1].textContent;
            alert("Viendo información de: " + userName);
            // Puedes también abrir un modal de solo lectura si lo necesitas
        });
    });

    // Acción: Editar usuario
    document.querySelectorAll(".btn.edit").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            openModal("edit", {
                firstName: row.children[1].textContent.split(" ")[0],
                lastName: row.children[1].textContent.split(" ").slice(1).join(" "),
                email: row.children[2].textContent,
                username: row.children[2].textContent.split("@")[0],
                role: row.querySelector(".role-badge").textContent.toLowerCase().trim(),
                status: row.querySelectorAll(".role-badge")[1].textContent.toLowerCase().trim()
            });
        });
    });

    // Acción: Resetear contraseña
    document.querySelectorAll(".btn.reset-password").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const email = row.children[2].textContent;
            if (confirm(`¿Deseas resetear la contraseña de ${email}?`)) {
                alert("Contraseña reseteada correctamente.");
                // Aquí iría llamada a tu backend o lógica real
            }
        });
    });

    // Acción: Activar usuario
    document.querySelectorAll(".btn.activate").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const name = row.children[1].textContent;
            alert(`${name} ha sido activado.`);
            // Puedes cambiar visualmente el estado si lo deseas
        });
    });

    // Acción: Desactivar usuario
    document.querySelectorAll(".btn.deactivate").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const name = row.children[1].textContent;
            alert(`${name} ha sido desactivado.`);
            // Cambiar el badge visualmente si lo deseas
        });
    });

    // Acción: Abrir modal de nuevo usuario
    document.querySelector(".add-provider").addEventListener("click", () => openModal("add"));
});

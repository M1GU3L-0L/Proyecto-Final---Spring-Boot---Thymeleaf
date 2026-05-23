/* ========================================
   VETERINARIA - VALIDACIONES Y FUNCIONES
   ======================================== */

// ========================================
// VALIDACIONES DE FORMULARIOS
// ========================================

document.addEventListener('DOMContentLoaded', function() {
    
    // Validación de formularios al enviar
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            let isValid = true;
            
            // Limpiar errores previos
            clearErrors(form);
            
            // Validar campos requeridos
            const requiredFields = form.querySelectorAll('[required]');
            requiredFields.forEach(field => {
                if (!validateRequired(field)) {
                    isValid = false;
                }
            });
            
            // Validar emails
            const emailFields = form.querySelectorAll('input[type="email"]');
            emailFields.forEach(field => {
                if (field.value && !validateEmail(field)) {
                    isValid = false;
                }
            });
            
            // Validar teléfonos
            const phoneFields = form.querySelectorAll('input[id*="telefono"]');
            phoneFields.forEach(field => {
                if (field.value && !validatePhone(field)) {
                    isValid = false;
                }
            });
            
            // Validar números positivos
            const numberFields = form.querySelectorAll('input[type="number"]');
            numberFields.forEach(field => {
                if (field.value && !validatePositiveNumber(field)) {
                    isValid = false;
                }
            });
            
            // Validar precios
            const priceFields = form.querySelectorAll('input[id*="precio"]');
            priceFields.forEach(field => {
                if (field.value && !validatePrice(field)) {
                    isValid = false;
                }
            });
            
            if (!isValid) {
                e.preventDefault();
                showGeneralError(form);
            }
        });
    });
    
    // Validación en tiempo real
    const inputs = document.querySelectorAll('input, select, textarea');
    inputs.forEach(input => {
        input.addEventListener('blur', function() {
            validateField(this);
        });
        
        input.addEventListener('input', function() {
            if (this.classList.contains('error')) {
                validateField(this);
            }
        });
    });
});

// ========================================
// FUNCIONES DE VALIDACIÓN
// ========================================

function validateRequired(field) {
    if (!field.value.trim()) {
        showError(field, 'Este campo es obligatorio');
        return false;
    }
    removeError(field);
    return true;
}

function validateEmail(field) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(field.value)) {
        showError(field, 'Ingresa un correo electrónico válido');
        return false;
    }
    removeError(field);
    return true;
}

function validatePhone(field) {
    const phoneRegex = /^[0-9]{10}$/;
    const cleanPhone = field.value.replace(/\s/g, '');
    if (!phoneRegex.test(cleanPhone)) {
        showError(field, 'El teléfono debe tener 10 dígitos');
        return false;
    }
    removeError(field);
    return true;
}

function validatePositiveNumber(field) {
    const value = parseFloat(field.value);
    if (isNaN(value) || value < 0) {
        showError(field, 'Ingresa un número positivo');
        return false;
    }
    removeError(field);
    return true;
}

function validatePrice(field) {
    const value = parseFloat(field.value);
    if (isNaN(value) || value <= 0) {
        showError(field, 'El precio debe ser mayor a 0');
        return false;
    }
    removeError(field);
    return true;
}

function validateField(field) {
    if (field.hasAttribute('required') && !validateRequired(field)) {
        return false;
    }
    
    if (field.type === 'email' && field.value && !validateEmail(field)) {
        return false;
    }
    
    if (field.id.includes('telefono') && field.value && !validatePhone(field)) {
        return false;
    }
    
    if (field.type === 'number' && field.value && !validatePositiveNumber(field)) {
        return false;
    }
    
    return true;
}

// ========================================
// MANEJO DE ERRORES
// ========================================

function showError(field, message) {
    field.classList.add('error');
    
    let errorElement = field.nextElementSibling;
    if (!errorElement || !errorElement.classList.contains('error-message')) {
        errorElement = document.createElement('div');
        errorElement.classList.add('error-message');
        field.parentNode.insertBefore(errorElement, field.nextSibling);
    }
    errorElement.textContent = message;
    errorElement.style.display = 'block';
}

function removeError(field) {
    field.classList.remove('error');
    const errorElement = field.nextElementSibling;
    if (errorElement && errorElement.classList.contains('error-message')) {
        errorElement.style.display = 'none';
    }
}

function clearErrors(form) {
    const errorFields = form.querySelectorAll('.error');
    errorFields.forEach(field => {
        field.classList.remove('error');
    });
    
    const errorMessages = form.querySelectorAll('.error-message');
    errorMessages.forEach(msg => {
        msg.style.display = 'none';
    });
}

function showGeneralError(form) {
    const existingAlert = form.querySelector('.alert-danger');
    if (existingAlert) {
        existingAlert.remove();
    }
    
    const alert = document.createElement('div');
    alert.classList.add('alert', 'alert-danger');
    alert.innerHTML = '<strong>⚠️ Error:</strong> Por favor corrige los campos marcados en rojo.';
    form.insertBefore(alert, form.firstChild);
    
    setTimeout(() => {
        alert.remove();
    }, 5000);
}

// ========================================
// CONFIRMACIONES
// ========================================

function confirmarEliminar(event, mensaje = '¿Estás seguro de que deseas eliminar este registro?') {
    if (!confirm(mensaje)) {
        event.preventDefault();
        return false;
    }
    return true;
}

// Agregar confirmación a todos los enlaces de eliminar
document.addEventListener('DOMContentLoaded', function() {
    const deleteLinks = document.querySelectorAll('a[href*="/eliminar/"]');
    deleteLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            confirmarEliminar(e);
        });
    });
});

// ========================================
// FORMATEO DE CAMPOS
// ========================================

// Formatear teléfonos automáticamente
document.addEventListener('DOMContentLoaded', function() {
    const phoneInputs = document.querySelectorAll('input[id*="telefono"]');
    phoneInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            if (value.length > 10) {
                value = value.slice(0, 10);
            }
            e.target.value = value;
        });
    });
});

// Formatear precios con separador de miles
document.addEventListener('DOMContentLoaded', function() {
    const priceDisplays = document.querySelectorAll('td:has(text()[contains(., "$")])');
    priceDisplays.forEach(cell => {
        const text = cell.textContent;
        const match = text.match(/\$\s*(\d+(?:\.\d+)?)/);
        if (match) {
            const number = parseFloat(match[1]);
            cell.textContent = text.replace(match[0], '$' + number.toLocaleString('es-CO', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            }));
        }
    });
});

// ========================================
// UTILIDADES
// ========================================

// Auto-focus en primer campo de formularios
document.addEventListener('DOMContentLoaded', function() {
    const firstInput = document.querySelector('form input:not([type="hidden"]):first-of-type');
    if (firstInput) {
        firstInput.focus();
    }
});

// Agregar animación a botones
document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('button, a.btn');
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            this.style.transform = 'scale(0.95)';
            setTimeout(() => {
                this.style.transform = '';
            }, 100);
        });
    });
});

// Mensajes de éxito temporales
document.addEventListener('DOMContentLoaded', function() {
    const successAlerts = document.querySelectorAll('.alert-success');
    successAlerts.forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'opacity 0.5s';
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 500);
        }, 5000);
    });
});

console.log('✅ Sistema de validaciones de Veterinaria cargado correctamente');
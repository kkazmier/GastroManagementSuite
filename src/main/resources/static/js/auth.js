function auth() {
    return {
        username: '',
        password: '',
        fullName: '',
        email: '',
        error: '',

        async login() {
            this.error = '';
            // ... bez zmian
        },

        async register() {
            this.error = '';
            try {
                const payload = {
                    username: this.username,
                    password: this.password,
                    fullName: this.fullName,
                    email: this.email
                };

                const res = await fetch('/api/auth/register', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                if (!res.ok) {
                    throw await res.text();
                }
                // po udanej rejestracji przekieruj do logowania
                window.location.href = 'login.html?registered';
            } catch (e) {
                this.error = typeof e === 'string' ? e : 'Błąd rejestracji';
            }
        }
    }
}

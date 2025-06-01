console.log("auth.js loaded");

function auth() {
    return {
        username: '',
        password: '',
        fullName: '',
        email: '',
        error: '',
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

                window.location.href = 'login.html?registered';
            } catch (e) {
                this.error = typeof e === 'string' ? e : 'Błąd rejestracji';
            }
        }
    };
}

window.auth = auth;
console.log("window.auth ustawione");

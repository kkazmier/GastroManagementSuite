function auth() {
    return {
        username: '',
        password: '',
        error: '',

        async login() {
            this.error = '';
            try {
                const res = await fetch('/api/auth/login', {   // <— zmiana tutaj
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        username: this.username,
                        password: this.password
                    })
                });
                if (!res.ok) throw await res.text();
                const { token } = await res.json();
                localStorage.setItem('jwt', token);
                window.location.href = 'index.html';
            } catch (e) {
                this.error = typeof e === 'string' ? e : 'Błąd logowania';
            }
        },

        async register() {
            this.error = '';
            try {
                const res = await fetch('/api/auth/register', {  // <— i tutaj
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        username: this.username,
                        password: this.password
                    })
                });
                if (!res.ok) throw await res.text();
                window.location.href = 'login.html?registered';
            } catch (e) {
                this.error = typeof e === 'string' ? e : 'Błąd rejestracji';
            }
        }
    }
}

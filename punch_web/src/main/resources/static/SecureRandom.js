var securerandom = function() {
    function G(J) {
        D[F++] ^= J & 255;
        D[F++] ^= J >> 8 & 255;
        D[F++] ^= J >> 16 & 255;
        D[F++] ^= J >> 24 & 255;
        F >= A && (F -= A)
    }
    var I, D, F, A = 256;
    if (D == null) {
        D = [];
        F = 0;
        var E;
        if (myBrowser() == "FF" && window.crypto) {
            var C = new ArrayBuffer(32);
            var H = new Int8Array(C);
            window.crypto.getRandomValues(H);
            for (E = 0; E < H.length; ++E) {
                D[E] = H[E] & 255
            }
        } else {
            if (myBrowser() == "IE11") {
                var C = new ArrayBuffer(32);
                var H = new Int8Array(C);
                var B = window.crypto || window.msCrypto;
                B.getRandomValues(H);
                for (E = 0; E < H.length; ++E) {
                    D[E] = H[E] & 255
                }
            } else {
                if (myBrowser() == "edge") {
                    var C = new ArrayBuffer(32);
                    var H = new Int8Array(C);
                    var B = window.crypto || window.msCrypto;
                    B.getRandomValues(H);
                    for (E = 0; E < H.length; ++E) {
                        D[E] = H[E] & 255
                    }
                } else {}
            }
        }
        F = 0;
        G((new Date).getTime())
    }
    this.nextBytes = function(L) {
        for (var N = 0; N < L.length; ++N) {
            var J = L, K = N, M;
            if (I == null) {
                G((new Date).getTime());
                I = new prng_newstate;
                I.init(D);
                for (F = 0; F < D.length; ++F) {
                    D[F] = 0
                }
                F = 0
            }
            M = I.next();
            J[K] = M
        }
    }
    this.nextInt = function(bound){
        if(bound <= 0){
            throw new Error("bound must be positive")
        }
        var r = this.next(31)
        var m = bound - 1
        if ((bound & m) == 0) 
            r = ((bound * r) >> 31)
        else {
            for (var u = r;
                 u - (r = u % bound) + m < 0;
                 u = this.next(31))
                ;
        }
        return r;
    }
    this.next = function(numBits){
        var numBytes = Math.floor((numBits+7)/8)
        var b = new Array(numBytes)
        var next = 0
        this.nextBytes(b)
        for (var i = 0; i < numBytes; i++) {
            next = (next << 8) + (b[i] & 0xFF);
        }
        return next >>> (numBytes*8 - numBits);
    }
} ,
prng_newstate = function() {
    this.j = this.i = 0;
    this.S = [];
    this.init = function(A) {
        for (var B, C, D = 0; D < 256; ++D) {
            this.S[D] = D
        }
        for (D = B = 0; D < 256; ++D) {
            B = B + this.S[D] + A[D % A.length] & 255,
            C = this.S[D],
            this.S[D] = this.S[B],
            this.S[B] = C
        }
        this.j = this.i = 0
    };
    this.next = function() {
        var A;
        this.i = this.i + 1 & 255;
        this.j = this.j + this.S[this.i] & 255;
        A = this.S[this.i];
        this.S[this.i] = this.S[this.j];
        this.S[this.j] = A;
        return this.S[A + this.S[this.i] & 255]
    }
}
function myBrowser() {
    var D = navigator.userAgent;
    var F = D.indexOf("Opera") > -1;
    var B = D.indexOf("compatible") > -1 && D.indexOf("MSIE") > -1;
    var E = D.indexOf("Edge") > -1 && !B;
    var C = D.indexOf("Trident") > -1 && D.indexOf("rv:11.0") > -1;
    var H = D.indexOf("Firefox") > -1;
    var A = D.indexOf("Safari") > -1;
    var G = D.indexOf("Chrome") > -1;
    if (E) {
        return "edge"
    }
    if (C) {
        return "IE11"
    }
    if (H) {
        return "FF"
    }
    if (G) {
        return "FF"
    }
}

function generateRandomCharacter(num) {
    var s = '';
    var randomchar = function() {
        var n = new securerandom().nextInt(62);
        if (n < 10)
            return n; 
        if (n < 36)
            return String.fromCharCode(n + 55); 
        return String.fromCharCode(n + 61);
    }
    while (s.length < num)
        s += randomchar();
    return s;
}


#include <secp256k1/secp256k1.h>
#include <iostream>
#include <cstring>
#include <sstream>
#include <assert.h>
#include <sys/random.h>

unsigned char hex2byte(char c)
{
    if (c >= '0' && c <= '9') {
        return c - '0';
    }
    else if (c >= 'a' && c <= 'f') {
        return c - 'a' + 10;
    }
    else if (c >= 'A' && c <= 'F') {
        return c - 'A' + 10;
    }
    return 0;
}

int fill_random(unsigned char* data, size_t size) {
#if defined(_WIN32)
    NTSTATUS res = BCryptGenRandom(NULL, data, size, BCRYPT_USE_SYSTEM_PREFERRED_RNG);
    if (res != STATUS_SUCCESS || size > ULONG_MAX) {
        return 0;
    }
    else {
        return 1;
    }
#elif defined(__linux__) || defined(__FreeBSD__)
    /* If `getrandom(2)` is not available you should fallback to /dev/urandom */
    ssize_t res = getrandom(data, size, 0);
    if (res < 0 || (size_t)res != size) {
        return 0;
    }
    else {
        return 1;
    }
#elif defined(__APPLE__) || defined(__OpenBSD__)
    /* If `getentropy(2)` is not available you should fallback to either
     * `SecRandomCopyBytes` or /dev/urandom */
    int res = getentropy(data, size);
    if (res == 0) {
        return 1;
    }
    else {
        return 0;
    }
#endif
    return 0;
}

void SignTrade(const char* trade_hash, const char* private_key, char*& signature)
{
    unsigned char randomize[32];
    secp256k1_ecdsa_signature sig;
    int return_val;

    secp256k1_context* ctx = secp256k1_context_create(SECP256K1_CONTEXT_NONE);

    if (!fill_random(randomize, sizeof(randomize))) {
        printf("Failed to generate randomness\n");
        return;
    }
    return_val = secp256k1_context_randomize(ctx, randomize);
    assert(return_val);

    unsigned char hash[32]{};
    if (strlen(trade_hash) != 64) {
        return;
    }
    else {
        for (size_t i = 0; i < 32; i++) {
            unsigned char fir = hex2byte(trade_hash[2 * i]);
            unsigned char sec = hex2byte(trade_hash[2 * i + 1]);
            hash[i] = (fir << 4) | sec;
        }
    }

    unsigned char seckey[32]{};
    if (strlen(private_key) != 64) {
        return;
    }
    else {
        for (size_t i = 0; i < 32; i++) {
            unsigned char fir = hex2byte(private_key[2 * i]);
            unsigned char sec = hex2byte(private_key[2 * i + 1]);
            seckey[i] = (fir << 4) | sec;
        }
    }


    /* Generate an ECDSA signature `noncefp` and `ndata` allows you to pass a
     * custom nonce function, passing `NULL` will use the RFC-6979 safe default.
     * Signing with a valid context, verified secret key
     * and the default nonce function should never fail. */
    return_val = secp256k1_ecdsa_sign(ctx, &sig, hash, seckey, NULL, NULL);
    assert(return_val);

    /* Serialize the signature in a compact form. Should always return 1
     * according to the documentation in secp256k1.h. */
    unsigned char serialized_signature[64];
    return_val = secp256k1_ecdsa_signature_serialize_compact(ctx, serialized_signature, &sig);
    assert(return_val);

    std::stringstream ss;
    for (size_t i = 0; i < 64; i++) {
        char c[5];
        sprintf(c, "%02x", serialized_signature[i]);
        ss << c;
    }

    signature = new char[129];
    strcpy(signature, ss.str().c_str());

    secp256k1_context_destroy(ctx);
}

int main() {
    char* sig;
    SignTrade("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"
        , "8F72F6B29E6E225A36B68DFE333C7CE5E55D83249D3D2CD6332671FA445C4DD3", sig);
    
    std::cout << sig << std::endl;

    
}
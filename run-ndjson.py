import requests
import psutil
import time
import os

URL = "http://localhost:8080/api/users/stream"  # tu endpoint NDJSON
OUTPUT_FILE = "users.ndjson"

def stream_users(url: str, output_path: str):
    process = psutil.Process(os.getpid())  # obtener proceso actual
    start_time = time.time()

    print(f"🚀 Iniciando descarga desde {url}")
    count = 0

    with requests.get(url, stream=True) as response:
        response.raise_for_status()
        with open(output_path, "w", encoding="utf-8") as f:
            for line in response.iter_lines():
                if line:
                    line_str = line.decode("utf-8")
                    f.write(line_str + "\n")
                    count += 1
                    if count % 10000 == 0:
                        mem_mb = process.memory_info().rss / (1024 * 1024)
                        cpu_percent = process.cpu_percent(interval=0.1)
                        print(f"Procesados {count:,} usuarios... "
                              f"Memoria: {mem_mb:.2f} MB | CPU: {cpu_percent:.1f}%")

    end_time = time.time()
    elapsed = end_time - start_time
    mem_final = process.memory_info().rss / (1024 * 1024)
    cpu_total = process.cpu_percent(interval=None)

    print("\n✅ Finalizado.")
    print(f"Total de usuarios guardados: {count:,}")
    print(f"⏱️  Tiempo total: {elapsed:.2f} segundos")
    print(f"🧠  Memoria usada: {mem_final:.2f} MB")
    print(f"⚙️  CPU (aprox): {cpu_total:.1f}%")

if __name__ == "__main__":
    stream_users(URL, OUTPUT_FILE)

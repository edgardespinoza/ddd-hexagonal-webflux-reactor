import requests
import psutil
import time
import os
import json

URL = "http://localhost:8080/api/users"  # tu endpoint JSON normal
OUTPUT_FILE = "users.json"

def fetch_json(url: str, output_path: str):
    process = psutil.Process(os.getpid())
    start_time = time.time()

    print(f"🚀 Iniciando descarga desde {url}")
    
    response = requests.get(url, headers={"Accept": "application/json"})
    response.raise_for_status()  # lanza error si el servidor responde != 200
    
    data = response.json()  # carga todo el JSON en memoria (⚠️)
    count = len(data) if isinstance(data, list) else 1

    # Guardar el JSON completo en disco
    with open(output_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

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
    fetch_json(URL, OUTPUT_FILE)

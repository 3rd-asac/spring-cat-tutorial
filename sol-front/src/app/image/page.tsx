"use client";
import Link from "next/link";
import { useRef, useState } from "react";
import Image from "next/image";
interface ImageInterface {
  id: string;
  url: string;
  width: number;
  height: number;
}

const FImages = () => {
  const idRef = useRef<HTMLInputElement>(null);
  const [list, setList] = useState<ImageInterface[]>([]);
  const [fav, setFav] = useState<String[]>([]);
  const [isDisabled, setIsDisabled] = useState(true);
  async function getImages(n: string) {
    const res = await fetch(
      "http://localhost:8080/api/v1/images?imageNumber=" + n
    );
    const data = await res.json();
    return data;
  }

  async function clickButton(id: string) {
    setFav([...fav, id]);
    console.log(fav);
    // 여기에서 선택한 이미지 ID(id)를 사용하여 원하는 작업을 수행할 수 있습니다.
  }

  async function handleFavourite() {
    fav.map(async (fa, idx) => {
      console.log(fa);
      const res = await fetch("http://localhost:8080/api/v1/favourite", {
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ image_id: fa, sub_id: "user-1" }),
      });
      const data = await res.json();
      console.log(data);
    });
  }

  async function handleSubmit() {
    if (idRef.current != null) {
      console.log("입력!");
      const data = await getImages(idRef.current.value);
      setList([...data]);
      console.log(data);
    }
  }

  return (
    <>
      <h1 className="text-3xl"> Cat Photos</h1>
      <Link className="text-2xl" href="/favourites">
        Cat favourites
      </Link>
      <form>
        <input placeholder="이미지 수 입력" ref={idRef} />
        <button type="button" onClick={handleSubmit}>
          입력
        </button>
      </form>
      <br />
      <button onClick={handleFavourite}>선택한 이미지 좋아요에 추가하기</button>
      <div>
        <ul>
          {list.map((li, index) => (
            <li key={li.id}>
              <img
                alt={`${li.id}`}
                src={li.url}
                width={`${li.width}`}
                height={`${li.height}`}
              />
              <button onClick={() => clickButton(li.id)}>Click Click</button>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};

export default FImages;
